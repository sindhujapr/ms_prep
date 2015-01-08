package lc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        sb.append(start);
        sb.append(',');
        sb.append(end);
        sb.append(']');
        return sb.toString();
    }
}

public class MergeIntervals {
    public static void main(String[] args) {
        ArrayList<Interval> intervals = new ArrayList<Interval>();
        intervals.add(new Interval(1, 3));
        ArrayList<Interval> result = new MergeIntervals().merge(intervals);
        for (Interval interval : result) {
            System.out.println(interval.start + ", " + interval.end);
        }
    }

    // my latest code
    public List<Interval> merge(List<Interval> intervals) {
        qsort(intervals, 0, intervals.size()-1);
        
        for(int i = 0; i < intervals.size(); ) {
            int j = i+1;
            int end = intervals.get(i).end;
            while(j < intervals.size() && intervals.get(j).start <= intervals.get(i).end) {
                intervals.get(i).end = Math.max(intervals.get(i).end, intervals.get(j).end);
                intervals.remove(j);
            }
            i = j;
        }
        
        return intervals;
    }
    
    private void qsort(List<Interval> intervals, int start, int end) {
        if(start >= end)
            return;
        
        int j = start;
        for(int i = start+1; i <= end; i++)
            if(intervals.get(i).start < intervals.get(start).start)
                swap(intervals, ++j, i);
        swap(intervals, j, start);
        
        qsort(intervals, start, j-1);
        qsort(intervals, j+1, end);
    }
    
    private void swap(List<Interval> intervals, int i, int j) {
        if(i == j)
            return;
        int start = intervals.get(i).start;
        int end = intervals.get(i).end;
        
        intervals.get(i).start = intervals.get(j).start;
        intervals.get(i).end = intervals.get(j).end;
        intervals.get(j).start = start;
        intervals.get(j).end = end;
    }

    /*
     * better than my code. The intervals need to be sorted to gain
     * better performance.
     */
    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval a, Interval b) {
                return a.start > b.start ? 1 : (a.start == b.start ? 0 : -1);
            }
        });

        ArrayList<Interval> res = new ArrayList<Interval>();

        int i = 0;
        while (i < intervals.size()) {
            int j = i + 1;
            int end = intervals.get(i).end;
            while (j < intervals.size() && end >= intervals.get(j).start) {
                end = Math.max(end, intervals.get(j).end);
                j++;
            }
            res.add(new Interval(intervals.get(i).start, end));
            i = j;
        }

        return res;
    }

    public ArrayList<Interval> merge1(ArrayList<Interval> intervals) {
        assert intervals != null;

        Collections.sort(intervals, new Comparator<Interval>() {
           @Override public int compare(Interval v1, Interval v2) {
               return v1.start > v2.start ? 1 : (v1.start == v2.start ? 0 : -1);
           } 
        });
        
        Iterator<Interval> iter = intervals.iterator();
        Interval prev = null;
        while(iter.hasNext()) {
            Interval interval = iter.next();
            
            if(prev == null || interval.start > prev.end) {
                prev = interval;
                continue;
            }
            
            if(interval.end <= prev.end) {
                iter.remove();
            } else {
                prev.end = interval.end;
                iter.remove();
            }
        }

        return intervals;
    }
    
    public ArrayList<Interval> merge2(ArrayList<Interval> intervals) {
        ArrayList<Interval> result = new ArrayList<Interval>();
        if (intervals == null || intervals.size() == 0)
            return result;

        Interval next = findNext(intervals);
        while (next != null) {
            if (result.size() == 0) {
                result.add(next);
            } else {
                Interval last = result.get(result.size() - 1);
                if (last.end >= next.start) {
                    result.remove(result.size() - 1);
                    result.add(new Interval(last.start,
                            next.end > last.end ? next.end : last.end));
                } else {
                    result.add(next);
                }

                next = findNext(intervals);
            }
        }
        return result;
    }

    private Interval findNext(ArrayList<Interval> intervals) {
        Interval ret = null;
        int index = -1;
        for (int i = 0; i < intervals.size(); i++) {
            Interval interval = intervals.get(i);
            if (ret == null) {
                ret = interval;
                index = i;
            } else {
                if (ret.start > interval.start
                        || (ret.start == interval.start && ret.end > interval.end)) {
                    ret = interval;
                    index = i;
                }
            }
        }
        if (index >= 0 && index < intervals.size())
            intervals.remove(index);
        return ret;
    }
}
