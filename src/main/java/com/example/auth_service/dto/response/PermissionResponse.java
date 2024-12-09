package com.example.auth_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionResponse {
    String name;
    String description;

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        else if(!(o instanceof PermissionResponse)) return false;
        else{
            PermissionResponse permissionResponse = (PermissionResponse) o;
            return this.getName().equals(((PermissionResponse) o).getName())
                    && this.getDescription().equals(((PermissionResponse) o).getDescription());
        }
    }
}
