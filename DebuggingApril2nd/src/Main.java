import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    /**
     * Developing: fix syntax errors, so it compiles<br>
     * Approaching Proficiency: all of the above and make if work as intended. <br>
     *    (by debugging and fixing logical errors, ie should not be rewriting large portions of code)<br>
     * Proficient: all of the above and explain what was wrong, and why the changes needed to be made,
     *    and provide an example set of inputs that would expose the original error.
     *    (you can/may need to provide multiple tests to cover all errors)
     *  <br><br>
     * This program should allow the user to add recorded evidence to each of the Essential Competency Areas
     * The user should also be able to display all evidence recorded so far.
     * The user should be able to display the modal grades for each Essential Competency Area
     * The user should be able to display the overall letter grade.
     */
    enum EvidenceType{
        DOCUMENTATION,
        SEARCHING_FOR_INFORMATION,
        DEBUGGING,
        THEORY_APPLICATION,
        EMPLOYABILITY
    }
    enum EvidenceScore{
        NOT_ENOUGH_EVIDENCE(0),
        DEVELOPING(1),
        APPROACHING_PROFICIENCY(2),
        PROFICIENT(3),
        EXCEPTIONAL(4);

        private final int value;
        private EvidenceScore(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    static HashMap<EvidenceType, List<EvidenceScore>> studentEvidence = new HashMap<>();
    static HashMap<EvidenceType,EvidenceScore> targetLevels = new HashMap<>();
    static HashMap<EvidenceScore, String> evidenceScoreName = new HashMap<>();

    public static void main(String[] args) {
        targetLevels.put(EvidenceType.DEBUGGING,EvidenceScore.APPROACHING_PROFICIENCY);
        targetLevels.put(EvidenceType.DOCUMENTATION,EvidenceScore.PROFICIENT);
        targetLevels.put(EvidenceType.EMPLOYABILITY,EvidenceScore.PROFICIENT);
        targetLevels.put(EvidenceType.THEORY_APPLICATION,EvidenceScore.PROFICIENT); // Added ;
        targetLevels.put(EvidenceType.SEARCHING_FOR_INFORMATION,EvidenceScore.APPROACHING_PROFICIENCY);

        evidenceScoreName.put(EvidenceScore.NOT_ENOUGH_EVIDENCE,"N");
        evidenceScoreName.put(EvidenceScore.DEVELOPING,"D");
        evidenceScoreName.put(EvidenceScore.APPROACHING_PROFICIENCY,"A");
        evidenceScoreName.put(EvidenceScore.PROFICIENT,"P");
        evidenceScoreName.put(EvidenceScore.EXCEPTIONAL,"E");

        studentEvidence.put(EvidenceType.DEBUGGING,new ArrayList<>());
        studentEvidence.put(EvidenceType.THEORY_APPLICATION,new ArrayList<>());
        studentEvidence.put(EvidenceType.DOCUMENTATION,new ArrayList<>());
        studentEvidence.put(EvidenceType.SEARCHING_FOR_INFORMATION,new ArrayList<>());
        studentEvidence.put(EvidenceType.EMPLOYABILITY,new ArrayList<>());

        Scanner scanner = new Scanner(System.in);

        while (true) { // Added parentheses around true
            System.out.println("Options:");
            System.out.println("1. Add Evidence");
            System.out.println("2. See All Evidence");
            System.out.println("3. See Overall Grade");
            System.out.println("4. See Modal Evidence Grades");
            System.out.println("5. Exit");
            System.out.println("Choose an option: ");
            // Declared data type
             int choice = getIntInRange(scanner,1,5);

            switch (choice) {
                case 1:

                    EvidenceType evidenceType = getEvidenceType(scanner);
                    EvidenceScore evidenceScore = getEvidenceScore(scanner);
                    studentEvidence.get(evidenceType).add(evidenceScore);
                    break;
                case 2:
                    displayEvidenceScores();
                    break;
                case 3:
                    System.out.println("Grade for MP is: " + calculateLetterGrade());
                    break;
                case 4:
                    displayModalGrades();
                    break;
                case 5:
                    System.out.println("Exiting program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please choose a valid option."); //should not happen

            }
        }
    }

    /**
     * Asks user for whole number and will retry until valid input is entered
     * @param scanner used to get input from the user
     * @return the valid integer entered
     */
    // Made method static
     static int getIntInRange(Scanner scanner,int min, int max){
        while(true) {
            try {
                int num = Integer.parseInt(scanner.nextLine());
                if(num < min && num > max){
                    System.out.println("integer not in valid range, must be between " +min + " and "+ max + " inclusive.");
                    continue;
                }
                return num;
            }catch (NumberFormatException ex){
                System.out.println("not an Integer, try again");
                continue;
            }
        }
    }

    /**
     * asks the user to enter the evidence score on the scale, will re-ask if the input is not valid
     * @param scanner used to get the user's input
     * @return the valid evidence score the user selected.
     */
    static EvidenceScore getEvidenceScore(Scanner scanner){
        while(true) {
            System.out.println("Enter the score for the ECA (N,D,A,P,E)");
            String evidenceScoreInput = scanner.nextLine().toUpperCase();
            if (evidenceScoreInput.matches("^[NDAPE]$")) {
                switch (evidenceScoreInput){
                    case "N": return EvidenceScore.NOT_ENOUGH_EVIDENCE;
                    case "D": return EvidenceScore.DEVELOPING;
                    case "A": return EvidenceScore.APPROACHING_PROFICIENCY;
                    case "P": return EvidenceScore.PROFICIENT;
                    case "E": return EvidenceScore.EXCEPTIONAL;
                    default:
                        System.out.println("ECA score not recognized");
                }
            }
        }
    }

    /**
     * asks the user to enter a number corresponding to an evidence type and returns the type they entered
     * will re-ask the user until a valid input is entered
     * @param scanner used to get the user input
     * @return the valid EvidenceType the user selected.
     */
    static EvidenceType getEvidenceType(Scanner scanner){
        EvidenceType evidenceType = null;
        while(evidenceType == null) {
            System.out.println("Which ECA is the evidence for?");
            System.out.println("1- Debugging");
            System.out.println("2- Theory and Application");
            System.out.println("3- Documentation");
            System.out.println("4- Searching for Information");
            System.out.println("5- Employability Skills");
            int evidenceTypeInput = getIntInRange(scanner,1,5);
            switch (evidenceTypeInput) {
                case 1 -> evidenceType = EvidenceType.DEBUGGING;
                case 2 -> evidenceType = EvidenceType.THEORY_APPLICATION;
                case 3 -> evidenceType = EvidenceType.DOCUMENTATION;
                case 4 -> evidenceType = EvidenceType.SEARCHING_FOR_INFORMATION;
                case 5 -> evidenceType = EvidenceType.EMPLOYABILITY;
            }
        }
        return evidenceType;
    }

    /**
     * displays all the scores that have been recorded for each evidence type.
     */
    static void displayEvidenceScores(){
        System.out.print("Debugging: ");
        displayList(studentEvidence.get(EvidenceType.DEBUGGING));
        System.out.println();
        System.out.print("Theory & Application: ");
        displayList(studentEvidence.get(EvidenceType.THEORY_APPLICATION));
        System.out.println();
        System.out.print("Documentation: ");
        displayList(studentEvidence.get(EvidenceType.DOCUMENTATION));
        System.out.println();
        System.out.print("Searching for Evidence: ");
        displayList(studentEvidence.get(EvidenceType.SEARCHING_FOR_INFORMATION));
        System.out.println();
        System.out.print("Employability Skills: ");
        displayList(studentEvidence.get(EvidenceType.EMPLOYABILITY));
        System.out.println();
    }

    /**
     * displays all the modal grades calculated for each evidence type
     */
    static void displayModalGrades(){
        System.out.print("Debugging: ");
        EvidenceScore debuggingScore = getModalGrade(studentEvidence.get(EvidenceType.DEBUGGING));
        System.out.println(evidenceScoreName.get(debuggingScore));
        System.out.print("Theory & Application: ");
        EvidenceScore theoryScore = getModalGrade(studentEvidence.get(EvidenceType.THEORY_APPLICATION));
        System.out.println(evidenceScoreName.get(theoryScore));
        System.out.print("Documentation: ");
        EvidenceScore documentationScore = getModalGrade(studentEvidence.get(EvidenceType.DOCUMENTATION));
        System.out.println(evidenceScoreName.get(documentationScore));
        System.out.print("Searching for Evidence: ");
        EvidenceScore searchingScore = getModalGrade(studentEvidence.get(EvidenceType.SEARCHING_FOR_INFORMATION));
        System.out.println(evidenceScoreName.get(searchingScore));
        System.out.print("Employability Skills: ");
        EvidenceScore employabilityScore = getModalGrade(studentEvidence.get(EvidenceType.EMPLOYABILITY)); //Changed getModalGrade evidence type to EMployability
        System.out.println(evidenceScoreName.get(employabilityScore));
    }

    /**
     * calculates the modal grade of a list of evidence types. Will return the highest level
     * where the number of evidence at that level or above is half or more of the total evidences recorded
     * @param evidenceScores the list of scores for the evidence type.
     * @return the modal score for the given Evidence Type, if less than 3 recorded evidence then it will return NOT_ENOUGH_EVIDENCE
     */
    static EvidenceScore getModalGrade(List<EvidenceScore> evidenceScores){
        //region calculate totals of each score
        if(evidenceScores.size()<3) return EvidenceScore.NOT_ENOUGH_EVIDENCE;
        int notEnoughEvidenceCount = 0;
        int developingCount =0;
        int approachingCount=0;
        int proficientCount = 0;
        int exceptionalCount = 0;
        for (int i = evidenceScores.size()-1; i >=0; i--) {
            switch (evidenceScores.get(i)) {
                case NOT_ENOUGH_EVIDENCE -> notEnoughEvidenceCount++;
                case DEVELOPING -> developingCount++;
                case APPROACHING_PROFICIENCY -> approachingCount++;
                case PROFICIENT -> proficientCount++;
                case EXCEPTIONAL -> exceptionalCount++;
            }
        }
        //endregion

        //region determine the modal grade based on evidence

        // return the grade where at least half the scores are equal to or above the given score.
        if(exceptionalCount >= (notEnoughEvidenceCount+developingCount+approachingCount+proficientCount)){
            return EvidenceScore.EXCEPTIONAL;
        }else if((exceptionalCount+proficientCount) >= (notEnoughEvidenceCount+developingCount+approachingCount)){
            return EvidenceScore.PROFICIENT;
        } else if ((exceptionalCount+proficientCount+approachingCount) >= (notEnoughEvidenceCount+developingCount)) {
            return EvidenceScore.APPROACHING_PROFICIENCY;
        } else if ((exceptionalCount+proficientCount+approachingCount+developingCount) >= (notEnoughEvidenceCount)) {
            return EvidenceScore.DEVELOPING;
        }else{
            return EvidenceScore.NOT_ENOUGH_EVIDENCE;
        }
        //endregion
    }

    /**
     * display a space separated list of all the evidence recorded for a given Evidence Type
     * @param evidenceScores the list of evidence to be printed.
     */
    static void displayList(List<EvidenceScore> evidenceScores){
        for (EvidenceScore evidenceScore: evidenceScores) {
            System.out.print(evidenceScoreName.get(evidenceScore) +" ");
        }
    }

    /**
     * calculates the letter grade for every level below target reduce letter grade by one( ie A->B)
     * for every level above increase grade by 1/3 (B->B+)
     * @return the String representing the grade based on the Target levels and modal grades.
     */
    static String calculateLetterGrade(){
        int gradeModifier = 0;
        gradeModifier += getGradeModifier(EvidenceType.DEBUGGING);
        gradeModifier += getGradeModifier(EvidenceType.DOCUMENTATION);
        gradeModifier += getGradeModifier(EvidenceType.SEARCHING_FOR_INFORMATION);
        gradeModifier += getGradeModifier(EvidenceType.EMPLOYABILITY);
        gradeModifier += getGradeModifier(EvidenceType.THEORY_APPLICATION); // Added += instead of =
        String grade = "";
       if(gradeModifier > 0) return "A+";
       switch (gradeModifier){
           case 0-> grade = "A";
           case -1-> grade = "A-";
           case -2-> grade = "B+";
           case -3-> grade = "B";
           case -4-> grade = "B-";
           case -5-> grade = "C+";
           case -6-> grade = "C";
           case -7-> grade = "C-";
           case -8-> grade = "D+";
           case -9-> grade = "D";
           case -10-> grade = "D-";
           default -> grade = "E";
       }
       return grade;
    }

    /**
     * gets the evidence modifier based on the modal grade for the evidence type and the target level
     * if the modal grade is less than the target grade multiply by 3 to represent 1 whole letter grade
     * otherwise return the difference (every level above is 1/3 letter grade increase)
     * @param evidenceType Essential competency area to see if student is at target level for
     * @return the grade modifier, scale:  1 -> 1/3 letter grade, -3 => grade is reduced by a letter grade
     */
    static int getGradeModifier(EvidenceType evidenceType){
        EvidenceScore evidenceScore = getModalGrade(studentEvidence.get(evidenceType));
        int evidenceModifier = evidenceScore.getValue()-targetLevels.get(evidenceType).getValue(); // Swapped evidence score and target levels so subtraction has the larger number first
        if(evidenceModifier<0) evidenceModifier*=3;
        return evidenceModifier;
    }

}
