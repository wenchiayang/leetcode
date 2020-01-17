/**
 * LC277
 */
public class LC277 {
    // Solution
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int findCelebrity(int n) {
        // find potential candidate
        int candidate = 0;
        for (int person = 0; person < n; person++) {
            // update candidate to person because previous candidate is known by a person
            if (knows(candidate, person)) { 
                candidate = person;
            }
        }
        
        // find invalid case
        // 1. candidate is not equal to him/herself
        // 2. candidate knows any person
        // 3. any person doesn't know the candidate
        for (int person = 0; person < n; person++) {
            if (candidate != person && knows(candidate, person) || !knows(person, candidate)) {
                return -1;
            }
        }

        return candidate;
    }

    boolean knows(int a, int b) {
        return true;
    }
}