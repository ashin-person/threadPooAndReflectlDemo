package ljx.ashin.thread;

import java.util.concurrent.*;

/**
 * 使用call<>的线程池</>
 * Created by Ashin Liang on 2017/12/18.
 */
public class threadPoolWithCallDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(8);
        Future<String> submitResult = null;
        CompletionService<String> completionService = new ExecutorCompletionService<String>(pool);
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            submitResult = completionService.submit(new Callable<String>() {
                /**
                 * Computes a result, or throws an exception if unable to do so.
                 * @return computed result
                 * @throws Exception if unable to compute a result
                 */
                public String call() throws Exception {
                    System.out.println(Thread.currentThread().getName()+" 正在执行任务，编号为:"+taskId);
                    Thread.sleep(2*1000);
                    return "taskId="+taskId+"执行完毕";
                }
            });

        }
        System.out.println("==任务提交完毕==");
        //从Future中get结果，这个方法是会被阻塞的，一直要等到线程任务返回结果
        System.out.println("获取到执行结果");
        for (int i =0;i<10;i++){
            System.out.println("88888888888");
            System.out.println("任务结果:"+completionService.take().get());
        }
        pool.shutdown();
    }
}
