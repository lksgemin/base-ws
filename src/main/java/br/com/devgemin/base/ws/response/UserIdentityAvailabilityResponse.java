package br.com.devgemin.base.ws.response;

public class UserIdentityAvailabilityResponse {
	private Boolean available;

    public UserIdentityAvailabilityResponse(Boolean available) {
        this.available = available;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
