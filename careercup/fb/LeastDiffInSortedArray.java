package careercup.fb;

/*
 * http://www.careercup.com/question?id=20982670
 */
public class LeastDiffInSortedArray {
    public static void main(String[] args) {
        int[] array = {1, 3, 7, 10, 11, 15, 30, 45, 50, 70};
        System.out.println(findLeastDiff(array, 20));
        System.out.println(findLeastDiff(array, 8));
        System.out.println(findLeastDiff(array, 40));
        System.out.println(findLeastDiff(array, 60));
        System.out.println(findLeastDiff(array, 2));
    }

    // my latest code   
    public static int findLeastDiff(int[] A, int target) {
        int start = 0, end = A.length-1;
        int index = -1;
        while(start <= end) {
            int mid = (start+end) >> 1;
            if(A[mid] == target) {
                return A[mid];
            } if(A[mid] > target) {
                if(mid == start) {
                    return A[mid];
                } else if(A[mid-1] < target) {
                    return Math.abs(A[mid-1]-target) > Math.abs(A[mid]-target) ?
                            A[mid] : A[mid-1];
                }
                end = mid - 1;
            } else if(A[mid] < target) {
                if(mid == end) {
                    return A[mid];
                } else if(A[mid+1] > target) {
                    return Math.abs(A[mid+1]-target) > Math.abs(A[mid]-target) ?
                            A[mid] : A[mid+1];
                }
                start = mid + 1;
            }
        }

        return -1;
    }

    public static int findLeastDiff(int[] array, int K) {
        int start = 0, end = array.length-1;
        int min = Integer.MAX_VALUE;
        int index = -1;
        while(start < end) {
            int mid = (start+end) >>> 1;
            if(min > Math.abs(array[mid]-K)) {
                min = Math.abs(array[mid]-K);
                index = mid;
            }
            
            if(array[mid] == K)
                return array[mid];
            else if(array[mid] > K)
                end = mid-1;
            else
                start = mid+1;
        }
        
        return array[index]; 
    }
}

Print a binary tree by vertical level order like   1 2 4 3 5 print : 3 2 1 5 4 
void getHDmap(treeNode* head, int hd, map<int,vector<treeNode*>> &hdMap){
    if (head==NULL)
        return;

    hdMap[hd].push_back(head);

    getHDmap(head->left, hd-1, hdMap);
    getHDmap(head->right, hd+1, hdMap);
}

void printVertical(treeNode* head){
    if (head==NULL)
        return;

    map<int, vector<treeNode*>> hdMap;
    int hd = 0;
    getHDmap(head, hd, hdMap);

    for (map<int, vector<treeNode*>>::iterator it = hdMap.begin(); it != hdMap.end(); it++){
        for (int i = 0; i < it->second.size(); i++){
            cout << it->second[i]->data << " ";
        }
        cout << endl;
    }
}
