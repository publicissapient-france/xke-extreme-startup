require 'sinatra/base'
require 'httparty'
require 'uuid'
require 'haml'
require 'socket'
require 'json'
require_relative 'game_state'
require_relative 'scoreboard'
require_relative 'player'
require_relative 'quiz_master'
require_relative 'mock_urls'

Thread.abort_on_exception = true

module ExtremeStartup
    
  class WebServer < Sinatra::Base

    def WebServer.local_ip
      UDPSocket.open {|s| s.connect("64.233.187.99", 1); s.addr.last}
    end

    set :port, 3000
    set :static, true 
    set :public, 'public'
    set :players,    Hash.new
    set :players_threads, Hash.new
    set :scoreboard, Scoreboard.new(ENV['LENIENT'])
    mock_urls = MockUrls.new("http://#{local_ip}:#{settings.port}/http_code")
    set :mock_urls, mock_urls
    set :question_factory, ENV['WARMUP'] ? WarmupQuestionFactory.new : QuestionFactory.new(mock_urls)
    set :game_state, GameState.new
    set :views, File.dirname(__FILE__) + "/../../views"

    get '/' do 
      haml :leaderboard, :locals => { 
          :leaderboard => LeaderBoard.new(scoreboard, players, game_state, request.ip),
          :players => players  }
    end

    get '/scores' do
      LeaderBoard.new(scoreboard, players, game_state, request.ip).to_json
    end

    class LeaderBoard
      def initialize(scoreboard, players, game_state, ip)
        @entries = []
        scoreboard.leaderboard.each do |entry| 
          @entries << LeaderBoardEntry.new(entry[0], players[entry[0]], entry[1], players[entry[0]].can_withdraw(ip))
        end
        @inplay = game_state.is_running?;
      end
      
      def each(&block)
        @entries.each &block
      end
      
      def to_json(*a)
        {'entries' => @entries, 'inplay' => @inplay }.to_json(*a)
      end
    end
    
    class LeaderBoardEntry
      attr_reader :playerid, :playername, :score, :can_withdraw
      def initialize(id, name, score, can_withdraw)
        @playerid = id;
        @playername = name;
        @score=score;
        @can_withdraw=can_withdraw;
      end
      
      def to_json(*a)
        {
          'playerid'   => playerid,
          'playername' => playername,
            'score' => score,
            'can_withdraw' => can_withdraw,
        }.to_json(*a)
      end
    end

    get '/graph' do 
      haml :scores
    end

    get '/controlpanel' do 
      haml :controlpanel, :locals => {
        :game_state => game_state,
        :round => question_factory.round.to_s
      }
    end
    
    get %r{/players/([\w]+)/metrics/score} do |uuid|
      if (players[uuid] == nil)
        haml :no_such_player
      else
        return "#{scoreboard.scores[uuid]}"
      end
    end

    get %r{/players/([\w]+)/metrics/correct} do |uuid|
      if (players[uuid] == nil)
        haml :no_such_player
      else
        return "#{scoreboard.current_total_correct(players[uuid])}"
      end
    end

    get %r{/players/([\w]+)/metrics/incorrect} do |uuid|
      if (players[uuid] == nil)
        haml :no_such_player
      else
        return "#{scoreboard.current_total_not_correct(players[uuid])}"
      end
    end

    get %r{/players/([\w]+)/metrics/requestcount} do |uuid|
      if (players[uuid] == nil)
        haml :no_such_player
      else
        return "#{scoreboard.total_requests_for(players[uuid])}"
      end
    end

    get %r{/players/([\w]+)/metrics} do |uuid|
      haml :metrics_index
    end

    get %r{/players/([\w]+)} do |uuid|
      if (players[uuid] == nil)
        haml :no_such_player
      else
        haml :personal_page, :locals => {
            :name => players[uuid].name,
            :playerid => uuid,
            :score => scoreboard.scores[uuid],
            :log => players[uuid].log[0..25],
            :can_withdraw => players[uuid].can_withdraw(request.ip)}
      end
    end

    get '/players' do
      haml :add_player
    end
    
    post '/advance_round' do
      question_factory.advance_round
    end
 
    post '/pause' do
      game_state.pause
    end
    
    post '/resume' do
      game_state.resume
    end

    get '/http_code/:token' do
      status = settings.mock_urls.status(params[:token]) || 404
      halt(status.to_i)
    end

    get %r{/withdraw/([\w]+)} do |uuid|
      if players[uuid].can_withdraw(request.ip)
        scoreboard.delete_player(players[uuid])
        players.delete(uuid)
        players_threads[uuid].kill
        players_threads.delete(uuid)
      end
      redirect '/'
    end
    
    post '/players' do
      player = Player.new(params)
      scoreboard.new_player(player)
      players[player.uuid] = player
      
      player_thread = Thread.new do
        QuizMaster.new(player, scoreboard, question_factory, game_state).start
      end
      players_threads[player.uuid] = player_thread

      haml :player_added, :locals => { :playerid => player.uuid }
    end
    
  private
    
    [:players, :players_threads, :scoreboard, :question_factory, :game_state].each do |setting|
      define_method(setting) do
        settings.send(setting)
      end
    end
  end
end
