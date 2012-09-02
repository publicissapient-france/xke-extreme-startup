require 'spec_helper'
require 'extreme_startup/question_factory'
require 'extreme_startup/player'

module ExtremeStartup
  describe FeetToMetersQuestion do
    let(:question) { FeetToMetersQuestion.new(Player.new) }

    it "converts to a string" do
      question.as_text.should =~ /how much is \d+ feet in meters/
    end

    context "when the number is 1" do
      let(:question) { FeetToMetersQuestion.new(Player.new, 0) }
    
      it "accept .3048 without zero" do
        question.answered_correctly?(".3048").should be_true
      end
   
      it "accept 0.30 with the zero" do
         question.answered_correctly?("0.30").should be_true
      end
    end

    context "when the numbers are known" do
      let(:question) { FeetToMetersQuestion.new(Player.new, 12 ) }

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

      it "doesn't crash when answer is non sensical string" do
        question.answered_correctly?("azerty").should be_false
      end
    end

  end
end
