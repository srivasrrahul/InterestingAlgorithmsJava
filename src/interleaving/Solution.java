package interleaving;

import java.util.HashMap;
import java.util.Objects;

class State {
    private int j1;
    private int j2;
    private int j3;

    public State(int j1, int j2, int j3) {
        this.j1 = j1;
        this.j2 = j2;
        this.j3 = j3;
    }

    public int getJ1() {
        return j1;
    }

    public int getJ2() {
        return j2;
    }

    public int getJ3() {
        return j3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return j1 == state.j1 && j2 == state.j2 && j3 == state.j3;
    }

    @Override
    public int hashCode() {
        return Objects.hash(j1, j2, j3);
    }
}
class StateMachine {
    HashMap<State,Boolean> stateValue = new HashMap<>();
    boolean isPresent(String s1, String s2, String s3,int j1,int j2,int j3) {
        if (j3 == s3.length()) {
            return  (j1 == s1.length() && j2 == s2.length());
        }else {

            State state = new State(j1,j2,j3);
            if (stateValue.containsKey(state)) {
                return stateValue.get(state);
            }
            if (j1 < s1.length()) {
                if (s1.charAt(j1) == s3.charAt(j3)) {
                    boolean r2 = isPresent(s1,s2,s3,j1+1,j2,j3+1);
                    if (r2 == true) {
                        stateValue.put(state,true);
                        return true;
                    }
                }
            }

            if (j2 < s2.length()) {
                if (s2.charAt(j2) == s3.charAt(j3)) {
                    boolean r3 = isPresent(s1,s2,s3,j1,j2+1,j3+1);
                    if (r3 == true) {
                        stateValue.put(state,true);
                        return true;
                    }
                }
            }

            stateValue.put(state,false);
            return false;
        }
    }
}
class Solution {
    boolean isPresent(String s1, String s2, String s3,int j1,int j2,int j3) {
        if (j3 == s3.length()) {
            return  (j1 == s1.length() && j2 == s2.length());
        }else {

            if (j1 < s1.length()) {
                if (s1.charAt(j1) == s3.charAt(j3)) {
                    boolean r2 = isPresent(s1,s2,s3,j1+1,j2,j3+1);
                    if (r2 == true) {
                        return true;
                    }
                }
            }

            if (j2 < s2.length()) {
                if (s2.charAt(j2) == s3.charAt(j3)) {
                    boolean r3 = isPresent(s1,s2,s3,j1,j2+1,j3+1);
                    if (r3 == true) {
                        return true;
                    }
                }
            }

            return false;
        }
    }
    public boolean isInterleave(String s1, String s2, String s3) {
        StateMachine statemMachine = new StateMachine();
        return statemMachine.isPresent(s1,s2,s3,0,0,0);
    }
}
