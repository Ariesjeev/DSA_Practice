class State {
    long score; 
    List<Integer> ids; 
    State(int sc, List<Integer> ids) {
        this.score = sc; 
        this.ids = ids; 
    }

    State(State s) {
        this.score = s.score; 
        this.ids = new ArrayList<>(s.ids);
    }

    State() {
        this.score = 0;
        this.ids = new ArrayList<>();
    }
}

class Intervals {
    int st, en, idx, weight; 
    Intervals(int s, int e, int i, int w) {
        st = s; 
        en = e; 
        idx = i; 
        weight = w; 
    }
}

class Solution {
    
    public int[] maximumWeight(List<List<Integer>> intervals) {
        // We are going to use DP + binary search here. 
        int n = intervals.size(); 
        List<Intervals> in = new ArrayList<>(); 
        for(int i = 0; i < n; i++) {
            in.add(new Intervals(intervals.get(i).get(0), intervals.get(i).get(1), i, intervals.get(i).get(2))); 
        }

        Collections.sort(in, (a, b) -> {
            if(a.en != b.en) return Integer.compare(a.en, b.en); 
            return Integer.compare(a.st, b.st); 
        }); 

        // Now for each index we need to store the intervals_cnt and their contributing max values. 
        // to find the non overlapping we will do the binary search 
        State[][] dp = new State[n + 1][5];

        // init dp 
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= 4; j++) {
                dp[i][j] = new State();
            }
        }

        for(int i = 1; i <= n; i++) {
            // bs to find last non-overlapping index 
            int target = in.get(i -1).st, lo = 0, hi = i - 2; // because we are 1 index based, cur = i - 1, prev = i - 2
            int pos = 0; // found nothing 
            while(lo <= hi) {
                int mid = (lo + hi) >> 1; 
                if(in.get(mid).en < target) {
                    pos = mid + 1; // convert to 1 based dp index 
                    lo = mid + 1; 
                } else {
                    // overlap 
                    hi = mid - 1; 
                }
            }

            // Now we have to update 1 that contributes less than current index score 

            for(int j = 1; j <= 4; j++) {
                // if we don't take cur, best from prev 
                dp[i][j] = new State(dp[i-1][j]); 

                // if we take current we have to update from last non overlapping one. 
                State prev = new State(dp[pos][j-1]); // one less interval 
                prev.score += in.get(i -1).weight;
                prev.ids.add(in.get(i-1).idx); 

                // sort the id's
                Collections.sort(prev.ids); // happens by ref 

                // is current yeilds better result
                update(dp[i][j], prev); 
            }

        }

        State bestState = dp[n][1];
        for (int j = 2; j <= 4; j++) {
            if (dp[n][j].score > bestState.score) {
                bestState = dp[n][j];
            } else if (dp[n][j].score == bestState.score && !dp[n][j].ids.isEmpty()) {
                if (isSmaller(dp[n][j].ids, bestState.ids)) {
                    bestState = dp[n][j];
                }
            }
        }

        int ansLen = dp[n][4].ids.size(); 
        int ans[] = new int[ansLen]; 
        for(int i = 0; i < ansLen; i++) ans[i] = dp[n][4].ids.get(i); 
        return ans; 
    }

    private void update(State cur, State candidate) {
        if(cur.score < candidate.score) {
            // update 
            cur.score = candidate.score; 
            cur.ids = new ArrayList<>(candidate.ids); 
        } else if(cur.score == candidate.score && !candidate.ids.isEmpty()) {
            //candidate id's lexicographically smaller
            if(isSmaller(candidate.ids, cur.ids)) {
                // update 
                cur.score = candidate.score; 
                cur.ids = new ArrayList<>(candidate.ids); 
            }
        }
    }

    private boolean isSmaller(List<Integer> a, List<Integer> b) {
        int len = Math.min(a.size(), b.size());

        for (int i = 0; i < len; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }
        return a.size() < b.size();
    }
}