package ru.bcs.bank.ms.courses.support.scheduler;

import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
public class RadissonRunnableTask implements Runnable, Serializable {

    private final SpELCommandRunner spELCommandRunner;
    private final String command;

    public void run() {
        spELCommandRunner.execute(command);
    }


}
