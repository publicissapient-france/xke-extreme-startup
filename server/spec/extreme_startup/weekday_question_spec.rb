require 'spec_helper'
require 'extreme_startup/question_factory'
require 'extreme_startup/player'

module ExtremeStartup
  describe WeekdayQuestion do
    let(:question) { WeekdayQuestion.new(Player.new) }

    it "converts to a string" do
      question.as_text.should =~ /which day of the week is [\s123]\d \w{3} \d{4}/i
    end

    it "gives correct number of points" do
      question.points.should == 50
    end

    context "when the date is known" do
      let(:question) { WeekdayQuestion.new(Player.new, Date.new(2013, 12, 7)) }

      it "converts to the right string" do
        question.as_text.should =~ /which day of the week is  7 Dec 2013/i
      end

      it "identifies a correct answer" do
        question.answered_correctly?("saturday").should be_true
      end

      it "identifies an incorrect answer" do
        question.answered_correctly?("monday").should be_false
      end
    end

  end
end
