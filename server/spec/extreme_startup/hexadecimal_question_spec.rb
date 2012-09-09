require 'spec_helper'
require 'extreme_startup/question_factory'
require 'extreme_startup/player'

module ExtremeStartup
  describe HexadecimalQuestion do
    let(:question) { HexadecimalQuestion.new(Player.new) }

    it "converts to a string" do
      question.as_text.should =~ /what is the decimal value of 0x[\da-f]+ plus 0x[\da-f]+/i
    end

    it "gives correct number of points" do
      question.points.should == 25
    end

    context "when the numbers are known" do
      let(:question) { HexadecimalQuestion.new(Player.new, 0xa1,0x1) }

      it "converts to the right string" do
        question.as_text.should == "what is the decimal value of 0xa1 plus 0x1"
      end

      it "identifies a correct answer" do
        question.answered_correctly?("162").should be_true
      end

      it "identifies an incorrect answer" do
        question.answered_correctly?("0xa2").should be_false
      end
    end

  end
end