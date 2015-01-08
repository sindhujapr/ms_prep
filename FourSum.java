package lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class FourSum {
	// my latest code based on three sum.
    public List<List<Integer>> fourSum(int[] num, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(num == null || num.length < 4)
            return res;
        
        Arrays.sort(num);
        
        for(int i = 0; i < num.length-3; i++) {
            if(i > 0 && num[i] == num[i-1])
                continue;
                
            for(int j = i+1; j < num.length-2; j++) {
                if(j > i+1 && num[j] == num[j-1])
                    continue;
                    
                int k = j+1, l = num.length-1;
                while(k < l) {
                    int sum = num[i]+num[j]+num[k]+num[l];
                    if(sum == target) {
                        List<Integer> temp = new ArrayList<Integer>();
                        temp.add(num[i]);
                        temp.add(num[j]);
                        temp.add(num[k]);
                        temp.add(num[l]);
                        res.add(temp);
                        
                        k++;
                        l--;
                    
                        while(k < l && num[k] == num[k-1])
                            k++;
                        while(k < l && num[l] == num[l+1])
                            l--;
                    } else if(sum < target) {
						// don't care whether we will have the same element next time since this is not result.
                        k++;
                    } else {
                        l--;
                    }
                }
            }
        }
        
        return res;
    }

	public static void main(String[] args) {
		int[] num = { 0, 0, 0, 0 };
		ArrayList<ArrayList<Integer>> result = new FourSum().fourSum(num, 0);
		System.out.println("result:");
		for (ArrayList<Integer> list : result)
			System.out.println(list);
	}

	/*
	 * this is my implementation but it doesn't pass test case {0, 0, 0, 0 } and 2.
	 * not sure why because it passes with my local environment.
	 */
    public ArrayList<ArrayList<Integer>> fourSum3(int[] num, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(num == null || num.length < 4)
            return res;
        
        Arrays.sort(num);
        
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        for(int i = 0; i < num.length-1; i++) {
            for(int j = i+1; j < num.length; j++) {
                int sum = num[i] + num[j];
                if(map.containsKey(sum)) {
                    List<Integer> temp = map.get(sum);
                    temp.add(i);
                    temp.add(j);
                } else {
                    List<Integer> temp = new ArrayList<Integer>();
                    temp.add(i);
                    temp.add(j);
                    map.put(sum, temp);
                }
            }
        }
        
        Set<Set<Integer>> set = new HashSet<Set<Integer>>();
        for(int sum : map.keySet()) {
            int complement = target-sum;
            if(map.containsKey(complement)) {
                List<Integer> temp1 = map.get(sum);
                List<Integer> temp2 = map.get(complement);
                
                for(int i = 0; i < temp1.size(); i += 2) {
                    for(int j = 0; j < temp2.size(); j+= 2) {
                        int i1 = temp1.get(i), i2 = temp1.get(i+1), i3 = temp2.get(j), i4 = temp2.get(j+1);

                        Set<Integer> s = new TreeSet<Integer>();
                        s.add(i1);
                        s.add(i2);
                        s.add(i3);
                        s.add(i4);
                        if(s.size() < 4 || set.contains(s))
                            continue;
                            
                        ArrayList<Integer> temp = new ArrayList<Integer>();
                        Iterator<Integer> iter = s.iterator();
                        while(iter.hasNext())
                            temp.add(num[iter.next()]);
                        res.add(temp);
                    }
                }
            }
        }
        
        return res;
    }
    
	public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
		Arrays.sort(num);
		Set<ArrayList<Integer>> hSet = new HashSet<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < num.length; i++) {
			for (int j = i + 1; j < num.length; j++) {
				for (int k = j + 1, l = num.length - 1; k < l;) {
					int sum = num[i] + num[j] + num[k] + num[l];
					if (sum > target) {
						l--;
					} else if (sum < target) {
						k++;
					} else if (sum == target) {
						ArrayList<Integer> found = new ArrayList<Integer>();
						found.add(num[i]);
						found.add(num[j]);
						found.add(num[k]);
						found.add(num[l]);
						if (!hSet.contains(found)) {
							hSet.add(found);
							result.add(found);
						}

						k++;
						l--;
					}
				}
			}
		}
		return result;
	}

	/*
	 * doesn't pass judge large
	 */
	public ArrayList<ArrayList<Integer>> fourSum1(int[] num, int target) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (num.length < 4)
			return result;

		Arrays.sort(num);
		for (int i = 0; i < num.length - 3;) {
			for (int j = i + 1; j < num.length - 2;) {
				int low = j + 1;
				int high = num.length - 1;

				while (low < high) {
					int low_bak = low;
					int high_bak = high;

					int sum = num[i] + num[j] + num[low] + num[high];
					if (sum == target) {
						ArrayList<Integer> one = new ArrayList<Integer>();
						one.add(num[i]);
						one.add(num[j]);
						one.add(num[low]);
						one.add(num[high]);
						result.add(one);

						low++;
						high--;
					} else if (sum < target) {
						low++;
					} else {
						high--;
					}

					/*
					 * remove possible duplicate
					 */
					while (low > low_bak && num[low] == num[low - 1]
							&& low < high)
						low++;
					while (high < high_bak && num[high] == num[high + 1]
							&& low < high)
						high--;
				}

				j++;
				while (j < num.length - 2 && num[j] == num[j - 1])
					j++;
			}

			i++;
			while (i < num.length - 3 && num[i] == num[i - 1])
				i++;
		}

		return result;
	}
}
