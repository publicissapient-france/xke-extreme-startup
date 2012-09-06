require 'spec_helper'
require 'extreme_startup/question_factory'
require 'extreme_startup/player'

module ExtremeStartup
  describe IntegerListQuestion do
    let(:question) { IntegerListQuestion.new(Player.new) }

    it "converts to a string" do
      question.as_text.should =~ /what is the product of \[([\d]+, ){9}[\d]+\]/i
    end

    it "gives correct number of points" do
      question.points.should == 40
    end

    context "when the numbers are known" do
      let(:question) { IntegerListQuestion.new(Player.new, 4019922771566567973, 135125992689353640, 3488286107611074299, 1918558737076766245, 469484964319780741) }

      it "converts to the right string" do
        question.as_text.should =~ /what is the product of \[4019922771566567973, 135125992689353640, 3488286107611074299, 1918558737076766245, 469484964319780741\]/i
      end

      it "identifies a correct answer" do
        question.answered_correctly?("1706732637465357733952487849889783293618621983375758183069514928799523205869378605640592600").should be_true
      end

      it "identifies an incorrect answer" do
        question.answered_correctly?("1").should be_false
      end
    end

  end
end