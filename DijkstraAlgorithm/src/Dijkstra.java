

public class Dijkstra {
    static final int MAX=100;
    static final int DELETED=99;
    static final int DUMMY=98;
    

    public static void main(String[] args) throws Exception {
    
         int[][] S = {{DELETED,DUMMY,DUMMY,DUMMY},{0,1,2,4},{0,2,5,DUMMY},
         {0,3,6,DUMMY},{0,4,5,6}};
         int[][] nodes = {{1,MAX,3,MAX,MAX,MAX},{0,2,MAX,4,MAX,MAX},
         {MAX,1,MAX,MAX,4,MAX},{MAX,MAX,0,MAX,MAX,4},{MAX,MAX,MAX,1,2,3}};
         int[] D = {10, MAX, 1, MAX};
         int[] R = {1, 0, 1, 0};
         int[] edges = {10,2,1,5,1,1};
         
         
    
    // While (Set s is not empty)
    while ( empty(S) == false) {
        // Choose a node u from S such that D[u] is minimum
        int u = 0;

        for (int i=0; i<4; i++) {
            System.out.println("D[" + i + "] =" + D[i]);  
          }

        for (int i=0; i<4; i++) {
            if (S[u][0] != DELETED) {
                
                    if (D[u] > D[i]) {
                        u = i;
                }
                

            }
            // if S[u] has been deleted increment u
            if (S[u][0] == DELETED) {
                u++;
            }
        }
        // Delete u from the set S
        S[u][0] = DELETED;

        int c;
        // for each node v such that (u,v) is an edge
        for (int i=1; i<3; i++) {
           if (S[i][0] != DELETED) {

                c = D[u] + edges[i];

                int v = nodes[u][i]-1;
                if (v < DELETED ) {
                    if (c < D[v]) {
                        R[v] = u;
                        D[v] = c;
                    }
                }
            }
        }
        
        

        System.out.println("u is " + u);
        
        for (int i=0; i<4; i++) {
            System.out.println("D[" + i + "] =" + D[i]);  
          }
     }
     
    }

    public static boolean empty(int[][] array) {
        for (int i=0; i<array.length; i++) {
            if ( array[i][0] != DELETED ) {
                return false;
            }
                
            }
            return true;
        }
    }

    