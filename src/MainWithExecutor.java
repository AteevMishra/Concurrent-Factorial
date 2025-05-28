import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//Here we calculate factorials using Executor Framework
public class MainWithExecutor {
    public static long factorial(long num)
    {
        long ans = 1;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for(long i = 1; i <= num; i++)
            ans *= i;

        return ans;
    }

    public static void main(String[] args) {

        //Initialize thread pool
        ExecutorService executor = Executors.newFixedThreadPool(3);
        long sT = System.currentTimeMillis();
        for(long i=1; i<=10; i++)
        {
            final long processNum = i;
            executor.submit(() -> {
                long ans = factorial(processNum);
                System.out.println(ans);
            });
        }

        //shutdown thread pool
        executor.shutdown();

        //await for termination
        try {
            while(!executor.awaitTermination(4, TimeUnit.SECONDS))
            {
                System.out.println("Waiting ...");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long eT = System.currentTimeMillis();
        System.out.println("Completed through Executors framework in : " + (eT - sT));

        //RESULTS
        //Using Executors Thread pool -
        // 10 threads : 3020 ms/ 3 sec (As expected, since we got same result using manual method)
        // 3 threads : 12041 ms/ 12 sec
    }
}
