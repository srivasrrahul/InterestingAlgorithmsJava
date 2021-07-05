package decodestrings;

class ReturnValue {
    private String m_Gen;
    private int m_ParsedTill;

    public ReturnValue(String m_Gen, int m_ParsedTill) {
        this.m_Gen = m_Gen;
        this.m_ParsedTill = m_ParsedTill;
    }

    public String getM_Gen() {
        return m_Gen;
    }

    public int getM_ParsedTill() {
        return m_ParsedTill;
    }
}
class Solution {
    ReturnValue gen(String s,int index) {
//        char ch = s.charAt(index);
//        if (ch == ']') {
//           return new ReturnValue("",index);
//        }

        StringBuilder current = new StringBuilder();
        int j = index;
        for (; j < s.length(); ) {
            char c = s.charAt(j);
            if (Character.isLetter(c)) {
                current.append(s.charAt(j));
                ++j;
            }else {
                //if its digit
                if (Character.isDigit(c)) {
                    //Extract all digit
                    StringBuilder num = new StringBuilder();
                    while (j < s.length() && Character.isDigit(s.charAt(index))) {
                        num.append(s.charAt(j));
                        j= j+1;
                    }
                    ReturnValue retValue = gen(s, j + 2);
                    int n = Integer.parseInt(num.toString());
                    for (int k = 0; k < n; ++k) {
                        current.append(retValue.getM_Gen());
                    }

                    j = retValue.getM_ParsedTill() + 1;
                }else {
                    //Now only one is ']'
                    break;
                    //return new ReturnValue(current.toString(),j);
                }
            }
        }

        return new ReturnValue(current.toString(),j);

    }
    public String decodeString(String s) {
        String netString = "1[" + s + "]";
        return gen(netString,0).getM_Gen();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.decodeString("3[abc2[pqr]]"));
    }
}
