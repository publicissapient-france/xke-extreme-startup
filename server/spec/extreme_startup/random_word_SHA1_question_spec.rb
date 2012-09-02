require 'spec_helper'
require 'extreme_startup/question_factory'
require 'extreme_startup/player'

module ExtremeStartup
  describe RandomWordSHA1Question do
    let(:question) { RandomWordSHA1Question.new(Player.new) }

    it "converts to a string" do
      question.as_text.should =~ /what is the sha1 for "(\w)+"/i
    end

    context "when the word is known" do
      let(:question) { RandomWordSHA1Question.new(Player.new, "startup") }

      it "converts to the right string" do
        question.as_text.should =~ /what is the sha1 for "startup"/i
      end

      it "identifies a correct answer" do
        question.answered_correctly?("71bcd582d7e88d66e44584b62597ec0560b7f591").should be_true
      end

      it "identifies an incorrect answer" do
        question.answered_correctly?("71bcd582d7e88d66e44584b62597ec0560b7f592").should be_false
      end
    end

  end
end