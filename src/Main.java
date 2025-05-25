public class Main {

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

        long sT = System.currentTimeMillis();
        for(long i=1; i<=12; i++)
        {
            factorial(i);
        }
        long eT = System.currentTimeMillis();
        System.out.println("Completed in : " + (eT - sT));

        //-----------------------MULTI - THREADING--------------------------------//
        Thread[] threads = new Thread[10];
        sT = System.currentTimeMillis();

        for(long i=1; i<=10; i++)
        {
            final long processNum = i;
            threads[(int)(i - 1)] = new Thread(()->{
                long ans = factorial(processNum);
                System.out.println(ans);
            });

            threads[(int)(i - 1)].start();
        }

        //Wait for completion of all threads
        for(Thread t: threads)
        {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        eT = System.currentTimeMillis();
        System.out.println("Completed through multi-threading in : " + (eT - sT));

        //RESULTS
        //Normal Process took : 36089 ms / 36 sec
        //Multi-threading Process took : 3023 ms / 3 sec
    }
}