package stuff;

public class Quine {
    static String end = "    static String lineSeparator = System.getProperty(\"line.separator\");\n" +
                        "    public static void main(String[] args) {\n" +
                        "        System.out.println(\"package stuff;\");\n" +
                        "        System.out.println(\"\");\n" +
                        "        System.out.println(\"public class Quine {\");\n" +
                        "        System.out.print  (\"    static String end = \\\"\");\n" +
                        "\n" +
                        "        for (char ch : end.toCharArray()) {\n" +
                        "            if (ch == '\\n') {\n" +
                        "                System.out.print(\"\\\\n\\\" +\" + lineSeparator + \"                        \\\"\");\n" +
                        "            } else if (ch =='\\\\') {\n" +
                        "                System.out.print(\"\\\\\\\\\");\n" +
                        "            } else if (ch =='\\\"') {\n" +
                        "                System.out.print(\"\\\\\\\"\");\n" +
                        "            } else {\n" +
                        "                System.out.print(ch);\n" +
                        "            }\n" +
                        "        }\n" +
                        "\n" +
                        "        end = end.replaceAll(\"\\n\", lineSeparator);\n" +
                        "\n" +
                        "        System.out.println(\"\\\";\");\n" +
                        "        System.out.print(end);\n" +
                        "    }\n" +
                        "}\n" +
                        "";
    static String lineSeparator = System.getProperty("line.separator");
    public static void main(String[] args) {
        System.out.println("package stuff;");
        System.out.println("");
        System.out.println("public class Quine {");
        System.out.print  ("    static String end = \"");

        for (char ch : end.toCharArray()) {
            if (ch == '\n') {
                System.out.print("\\n\" +" + lineSeparator + "                        \"");
            } else if (ch =='\\') {
                System.out.print("\\\\");
            } else if (ch =='\"') {
                System.out.print("\\\"");
            } else {
                System.out.print(ch);
            }
        }

        end = end.replaceAll("\n", lineSeparator);

        System.out.println("\";");
        System.out.print(end);
    }
}
