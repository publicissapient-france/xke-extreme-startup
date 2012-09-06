require 'spec_helper'
require 'extreme_startup/mock_urls'

module ExtremeStartup
  describe MockUrls do
    let(:mock_urls) { MockUrls.new('http://test.com') }

    it 'generates a joke question for code 418' do
      mock_urls.urls_by_status[418].should == 'a teapot'
    end

    it 'generates random URLs for other codes' do
      MockUrls::Statuses.each do |status|
        url = mock_urls.urls_by_status[status]
        url.should =~ /http:\/\/test.com\/[a-z0-9]+/

        /http:\/\/test.com\/(?<token>[a-z0-9]+)/ =~ url
        mock_urls.status(token).should == status
      end
    end

    it 'returns a sample status and url' do
      status, url = mock_urls.sample_status_and_url
      MockUrls::Statuses.include?(status).should be_true
      url.should =~ /http:\/\/test.com\/[a-z0-9]+/

      /http:\/\/test.com\/(?<token>[a-z0-9]+)/ =~ url
      mock_urls.status(token).should == status
    end
  end
end
