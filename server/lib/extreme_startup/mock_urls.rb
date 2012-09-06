class MockUrls
  Statuses = [ 200, 201, 202, 203, 204, 205, 206,
               400, 401, 402, 403, 404, 405, 406,
               407, 408, 409, 410, 411, 412, 413,
               414, 415, 416, 417,
               500, 501, 502, 503, 504, 505 ]

  attr_reader :urls_by_status # for testing

  def initialize(base_url)
    @urls_by_status = {}
    @statuses_by_token = {}
    Statuses.each do |status|
        token = random_token
        url = "#{base_url}/#{token}"
        puts "#{url} => #{status}"
        @urls_by_status[status] = url
        @statuses_by_token[token] = status
    end
    # A small twist
    @urls_by_status[418] = 'a teapot'
  end

  def sample_status_and_url
    @urls_by_status.entries.sample
  end

  def status(token)
    @statuses_by_token[token]
  end

  private

  def random_token
    # http://blog.logeek.fr/2009/7/2/creating-small-unique-tokens-in-ruby
    rand(36**8).to_s(36)
  end
end
