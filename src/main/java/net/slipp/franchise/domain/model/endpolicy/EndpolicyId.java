/**
 * Created by joenggyu0@gmail.com on 4/7/20
 * Github : http://github.com/lenkim
 */

package net.slipp.franchise.domain.model.endpolicy;

import java.util.Objects;

public class EndpolicyId {

    private final String value;

    public static EndpolicyId of(String value){

        EndpolicyId endpolicyId = new EndpolicyId(value);
        
        return endpolicyId;
    }

    EndpolicyId(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EndpolicyId EndpolicyId = (EndpolicyId) o;
        return Objects.equals(value, EndpolicyId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
