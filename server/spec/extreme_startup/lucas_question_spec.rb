require 'spec_helper'
require 'extreme_startup/question_factory'
require 'extreme_startup/player'

module ExtremeStartup
  describe LucasQuestion do
    let(:question) { LucasQuestion.new(Player.new, 14) }
    
    it "converts to a string" do
      question.as_text.should =~ /what is the 15th Prime Lucas number/i
    end
    
    context "when the numbers are known" do
      let(:question) { LucasQuestion.new(Player.new, 5) }
        
      it "converts to the right string" do
        question.as_text.should =~ /what is the 6th Prime Lucas number/i
      end
      
      it "identifies a correct answer" do
        question.answered_correctly?("47").should be_true
      end

      it "identifies an incorrect answer" do
        question.answered_correctly?("5").should be_false
      end
    end
    
    context "when the numbers are large" do
      let(:question) { LucasQuestion.new(Player.new, 20) }

      it "converts to the right string" do
        question.as_text.should =~ /what is the 21st Prime Lucas number/i
      end

      it "identifies a correct answer" do
        question.answered_correctly?("258899611203303418721656157249445530046830073044201152332257717521").should be_true
      end

      it "identifies an incorrect answer" do
        question.answered_correctly?("258899611203303418721656157249445530046830073044201152332257717522").should be_false
      end
    end
    
  end
end
