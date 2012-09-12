require 'spec_helper'
require 'extreme_startup/question_factory'
require 'extreme_startup/player'

module ExtremeStartup
  describe AlphagramQuestion do
    let(:question) { AlphagramQuestion.new(Player.new) }

    it "converts to a string" do
      question.as_text.should =~ /what is the Alphagram of "\w+"/i
    end

    context "when the words are known" do
      let(:question) { AlphagramQuestion.new(Player.new, "mississippi") }

      it "converts to the right string" do
        question.as_text.should =~ /what is the Alphagram of "mississippi"/i
      end

      it "identifies a correct answer" do
        question.answered_correctly?("iiiimppssss").should be_true
      end

      it "identifies an incorrect answer" do
        question.answered_correctly?("iiiimppsssse").should be_false
      end
    end

  end
end
