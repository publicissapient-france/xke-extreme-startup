require 'spec_helper'
require 'extreme_startup/question_factory'
require 'extreme_startup/player'

module ExtremeStartup
  describe PiQuestion do
    let(:question) { PiQuestion.new(Player.new) }

    it "converts to a string" do
      question.as_text.should =~ /what is the \d*(1st|2nd|3rd|[04-9]th) decimal of Pi/
    end

    it "gives correct number of points" do
      question.points.should == 30
    end

    context "when the numbers are known" do
      let(:question) { PiQuestion.new(Player.new, 11) }

      it "converts to the right string" do
        question.as_text.should == "what is the 12nd decimal of Pi"
      end

      it "identifies a correct answer" do
        question.answered_correctly?("9").should be_true
      end

      it "identifies an incorrect answer" do
        question.answered_correctly?("3").should be_false
      end
    end

  end
end
