package name.svetov.userrelationships.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum UserRelationshipsEnum {
    FRIENDSHIP("FRIENDSHIP");

    private final String type;
}
