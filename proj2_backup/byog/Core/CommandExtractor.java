package byog.Core;

public class CommandExtractor {
    public static char[] extractCommand(String input) {
        if (input == null || input.length() < 3) {
            // 如果输入为空或长度小于3（不能满足n/l + 一个字符 + 其他字符的格式），返回空数组
            return new char[0];
        }

        char prefix = input.charAt(0);
        if (prefix == 'n' || prefix == 'l') {
            // 提取去掉前缀后的字符串
            String remainingString = input.substring(1);

            // 找到第一个非数字字符的位置
            int firstNonDigitIndex = 0;
            while (firstNonDigitIndex < remainingString.length() && Character.isDigit(remainingString.charAt(firstNonDigitIndex))) {
                firstNonDigitIndex++;
            }

            // 从第一个非数字字符开始提取子字符串
            if (firstNonDigitIndex < remainingString.length()) {
                String commandString = remainingString.substring(firstNonDigitIndex);
                return commandString.toCharArray();
            }
        }

        // 如果输入不以n或l开头，或没有有效的命令部分，返回空数组
        return new char[0];
    }

    public static void main(String[] args) {
        String input1 = "n123sswwdasdassadwas";
        String input2 = "n123sss:q";
        String input3 = "lwww";
        String input4 = "abc"; // 无效输入示例
        String input5 = "n1"; // 无效输入示例

        char[] command1 = extractCommand(input1);
        char[] command2 = extractCommand(input2);
        char[] command3 = extractCommand(input3);
        char[] command4 = extractCommand(input4);
        char[] command5 = extractCommand(input5);

        // 打印结果
        System.out.println("Command 1: " + new String(command1));
        System.out.println("Command 2: " + new String(command2));
        System.out.println("Command 3: " + new String(command3));
        System.out.println("Command 4: " + new String(command4));
        System.out.println("Command 5: " + new String(command5));
    }
}


