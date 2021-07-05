package insertinterval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        ArrayList<int []> arrayList = new ArrayList<>();
        boolean newPending = true;
        for (int j = 0;j<intervals.length;++j) {
            if (newPending == false) {
                arrayList.add(intervals[j]);
            }else {
                if (newInterval[0] < intervals[j][0]) {
                    arrayList.add(newInterval);
                    arrayList.add(intervals[j]);
                    newPending = false;
                }else {
                    arrayList.add(intervals[j]);
                }
            }

        }

        if (newPending == true) {
            arrayList.add(newInterval);
            newPending = false;
        }

        //arrayList.add(newInterval);
        //Collections.sort(arrayList, (o1, o2) -> Integer.compare(o1[0], o2[0]));

        //System.out.println("");
        ArrayList<int []> lst = new ArrayList<>();
        lst.add(arrayList.get(0));

        for (int j = 1;j<arrayList.size();++j) {
            int [] last = lst.remove(lst.size()-1);
            int [] current = arrayList.get(j);

            //System.out.println(Arrays.toString(last) + "," + Arrays.toString(current));

            if (current[0] > last[1]) {
                lst.add(last);
                lst.add(current);
            }else {
                int [] converge = {last[0],Math.max(last[1],current[1])};
                lst.add(converge);
            }
        }

//         for (int [] x : lst) {
//             System.out.print(Arrays.toString(x) + ",");
//         }

//         System.out.println();
        int [][] retValye = new int[lst.size()][2];
        lst.toArray(retValye);
        return retValye;
    }
}
