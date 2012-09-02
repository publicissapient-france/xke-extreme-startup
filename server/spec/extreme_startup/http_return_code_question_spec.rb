require 'spec_helper'
require 'extreme_startup/question_factory'
require 'extreme_startup/player'

module ExtremeStartup
  describe HttpResponseQuestion do
    let(:question) { HttpResponseQuestion.new(Player.new, "http://localhost:3000/") }

    it "converts to a string" do
      question.as_text.should =~ /what is the http response code of http:\/\/\w+(:\d+)?\/http_code\/\?uid=\w+/i
    end

    context "when the word is known" do
      let(:question) { HttpResponseQuestion.new(Player.new,"http://localhost:3000/", "startup", 500) }

      it "converts to the right string" do
        question.as_text.should =~ /what is the http response code of http:\/\/localhost:3000\/http_code\/\?uid=startup/i
      end

      it "identifies a correct answer" do
        question.answered_correctly?("500").should be_true
      end

      it "identifies an incorrect answer" do
        question.answered_correctly?("71bcd582d7e88d66e44584b62597ec0560b7f592").should be_false
      end
    end

  end
end