package byog.Core;

public class CommandExtractor {
    public static char[] extractCommand(String input) {
        if (input == null || input.length() < 3) {
            return new char[0];
        }

        char prefix = input.charAt(0);
        if (prefix == 'n' || prefix == 'l') {
            String remainingString = input.substring(1);

            int firstNonDigitIndex = 0;
            while (firstNonDigitIndex < remainingString.length()
                    && Character.isDigit(remainingString.charAt(firstNonDigitIndex))) {
                firstNonDigitIndex++;
            }

            if (firstNonDigitIndex < remainingString.length()) {
                String commandString = remainingString.substring(firstNonDigitIndex);
                return commandString.toCharArray();
            }
        }

        return new char[0];
    }

    public static void main(String[] args) {
        String input1 = "n123sswwdasdassadwas";
        String input2 = "n123sss:q";
        String input3 = "lwww";
        String input4 = "abc";
        String input5 = "n1";

        char[] command1 = extractCommand(input1);
        char[] command2 = extractCommand(input2);
        char[] command3 = extractCommand(input3);
        char[] command4 = extractCommand(input4);
        char[] command5 = extractCommand(input5);

        System.out.println("Command 1: " + new String(command1));
        System.out.println("Command 2: " + new String(command2));
        System.out.println("Command 3: " + new String(command3));
        System.out.println("Command 4: " + new String(command4));
        System.out.println("Command 5: " + new String(command5));
    }
}


