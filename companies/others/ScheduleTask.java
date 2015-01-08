package google;

/**
 * http://www.careercup.com/question?id=6282171643854848
 *
 * Not sure how to use DP
 */
public class ScheduleTask {
    public static void main(String[] args) {
//        int[] servers = {6, 7};
//        int[] tasks = {5, 3, 2, 3};

        int[] servers = new int[] { 8, 16, 8, 32 };
        int[] tasks = new int[] {18, 4, 8, 4, 6, 6, 8, 8 };

        System.out.println(scheduleTask(servers, tasks, 0));

    }

    public static boolean scheduleTask(int[] servers, int[] tasks, int start) {
        if(start == tasks.length)
            return true;

        for(int i = 0; i < servers.length; i++) {
            if(servers[i] >= tasks[start]) {
                servers[i] -= tasks[start];
                if(scheduleTask(servers, tasks, start+1))
                    return true;

                servers[i] += tasks[start];
            }
        }
        return false;
    }
}
