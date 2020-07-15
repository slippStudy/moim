/**
 * Created by joenggyu0@gmail.com on 4/7/20
 * Github : http://github.com/lenkim
 */

package net.slipp.moim.domain.model.recruit;

public enum Status {

    BEGIN {
        public boolean isBegun() {
            return true;
        }
    },
    WORKING {
        public boolean isWorking() {
            return true;
        }
    },
    FINISH {
        public boolean isFinished() {
            return true;
        }
    };

    public boolean isBegun() {
        return false;
    }

    public boolean isWorking() {
        return false;
    }

    public boolean isFinished() {
        return false;
    }


}
