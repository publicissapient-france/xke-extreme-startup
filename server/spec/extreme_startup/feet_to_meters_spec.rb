require 'spec_helper'
require 'extreme_startup/question_factory'
require 'extreme_startup/player'

module ExtremeStartup
  describe FeetToMetersQuestion do
    let(:question) { FeetToMetersQuestion.new(Player.new) }

    it "converts to a string" do
      question.as_text.should =~ /how much is \d+ feet in meters/
    end

    context "when the numbers are known" do
      let(:question) { FeetToMetersQuestion.new(Player.new, 12, 8) }

      it "converts to the right string" do
        question.as_text.should == "how much is 13 feet in meters"
      end

      it "identifies a correct answer" do
        question.answered_correctly?("3.9624").should be_true
      end

      it "accepts rounded answers up to 2 decimal" do
        question.answered_correctly?("3.96").should be_true
      end

      it "accepts a precision of 2 decimal" do
        question.answered_correctly?("3.9697").should be_true
      end

      it "identifies an incorrect answer" do
        question.answered_correctly?("0.4").should be_false
      end
    end

  end
end
