package mocktail;


import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

class TaskRejectHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.printf("tasks.RejectedTaskHandler: The beverage request %s has been rejected by coffee machine", r.toString());
    }
}