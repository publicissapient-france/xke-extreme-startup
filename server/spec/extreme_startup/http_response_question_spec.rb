require 'spec_helper'
require 'extreme_startup/question_factory'
require 'extreme_startup/player'

module ExtremeStartup
  describe HttpResponseQuestion do
    let(:question) {
      mock_urls = double('sample_status_and_url' => [404, 'http://foobar.com'])
      HttpResponseQuestion.new(Player.new, mock_urls)
    }

    it "converts to a string" do
      question.as_text.should == "what HTTP response code do you get when you send a GET request to http://foobar.com"
    end

    it "identifies a correct answer" do
      question.answered_correctly?("404").should be_true
    end

    it "identifies an incorrect answer" do
      question.answered_correctly?("403").should be_false
    end
  end
end
