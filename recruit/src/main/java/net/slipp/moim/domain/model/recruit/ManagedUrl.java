package net.slipp.moim.domain.model.recruit;

import org.apache.logging.log4j.util.Strings;

public class ManagedUrl {

    public static final ManagedUrl NO_CONTENT = new ManagedUrl(null, Strings.EMPTY);

    private RecruitId recruitId;
    private String url;

    public ManagedUrl(RecruitId recruitId, String url) {
        this.recruitId = recruitId;
        this.url = url;
    }
}
