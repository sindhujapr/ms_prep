package lc;

import java.util.ArrayList;

public class InsertInterval {
    static class Interval {
        int start, end;

        public Interval(int s, int e) { start = s;  end = e; }
        @Override public String toString() { return "[" + start + "," + end + "]"; }
    }
    
    public static void main(String[] args) {
        InsertInterval instance = new InsertInterval();

        ArrayList<Interval> result = instance.insert1(instance.init(), new Interval(6, 8));
        for (Interval interval : result)
            System.out.println(interval);
    }

    public ArrayList<Interval> init() {
        ArrayList<Interval> intervals = new ArrayList<Interval>();
        intervals.add(new Interval(1, 5));
        intervals.add(new Interval(7, 9));
        intervals.add(new Interval(12, 15));
        intervals.add(new Interval(18, 22));
        intervals.add(new Interval(27, 29));
        return intervals;
    }
    
    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        int i1 = searchPosition(intervals, newInterval.start);
        int i2 = searchPosition(intervals, newInterval.end);
        
        int startIndex = i1 >= 0 ? i1 : 0;
        int endIndex = i2 >= 0 ? i2 : 0;
        for(int cnt = 0; cnt < endIndex-startIndex; cnt++)
            intervals.remove(startIndex);
       
        intervals.add(startIndex, newInterval);
        if(startIndex > 0 && newInterval.start <= intervals.get(startIndex-1).end) {
            intervals.get(startIndex-1).end = Math.max(newInterval.end, intervals.get(startIndex-1).end);
            intervals.remove(startIndex);
            startIndex--;
        }
        
        if(startIndex < intervals.size()-1 && intervals.get(startIndex).end >= intervals.get(startIndex+1).start) {
            intervals.get(startIndex).end = Math.max(intervals.get(startIndex).end, intervals.get(startIndex+1).end);
            intervals.remove(startIndex+1);
        }

        return intervals;
    }

    /*
     * seems better
     */
    public ArrayList<Interval> insert1(ArrayList<Interval> intervals, Interval newInterval) {
        if (intervals.size() == 0)
            intervals.add(newInterval);
        int startPos = searchPosition(intervals, newInterval.start);
        int endPos = searchPosition(intervals, newInterval.end);

        System.out.println(startPos + "\t" + endPos);
        
        int newStart, newEnd;
        if (startPos >= 0 && intervals.get(startPos).end >= newInterval.start) {
            newStart = intervals.get(startPos).start;
        } else {
            newStart = newInterval.start;
            startPos++;
        }
        
        if (endPos >= 0)
            newEnd = Math.max(newInterval.end, intervals.get(endPos).end);
        else
            newEnd = newInterval.end;

        for (int i = startPos; i <= endPos; i++)
            intervals.remove(startPos);
        intervals.add(startPos, new Interval(newStart, newEnd));
        return intervals;
    }

    /*
     * return the index of the interval whose "start" is less than or
     * equal to x. the returned index could be -1 if even the "start" of
     * the first interval is less than or equal to x.
     */
    public int searchPosition(ArrayList<Interval> intervals, int x) {
        int a = 0, b = intervals.size() - 1;
        while (a <= b) {
            int mid = (a + b) / 2;
            if (intervals.get(mid).start == x)
                return mid;
            else if (intervals.get(mid).start > x)
                b = mid - 1;
            else
                a = mid + 1;
        }
        return b;
    }
}
