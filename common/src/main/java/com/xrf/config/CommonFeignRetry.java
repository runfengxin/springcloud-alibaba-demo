package com.xrf.config;

import feign.RetryableException;
import feign.Retryer;
import lombok.extern.slf4j.Slf4j;

import static java.util.concurrent.TimeUnit.SECONDS;

@Slf4j
public class CommonFeignRetry extends Retryer.Default {
 
    private static final String METRICS_KEY = "feign_retry_count";
 
    private static final String APOLLO_CONFIG_KEY = "feign.retry.enable";

    private Boolean retryEnable;
 
//    private final Counter metrics = Counter.build().name("METRICS_KEY").help("total sql command.").labelNames(METRICS_KEY).register();
 
    public CommonFeignRetry(boolean retryEnable) {
        //重试5次 最大间隔时间1秒
        this(100, SECONDS.toMillis(1), 5, retryEnable);
    }
 
    public CommonFeignRetry(long period, long maxPeriod, int maxAttempts, boolean retryEnable) {
        super(period, maxPeriod, maxAttempts);
        this.retryEnable = retryEnable;
    }
 
    @Override
    public void continueOrPropagate(RetryableException e) {
        if (Boolean.FALSE.equals(retryEnable)) {
            throw e;
        }
//        metrics.inc();
        log.warn("【FeignRetryAble】Message【{}】", e.getMessage());
        super.continueOrPropagate(e);
    }
 
    @Override
    public Retryer clone() {
        return new CommonFeignRetry(retryEnable);
    }
}