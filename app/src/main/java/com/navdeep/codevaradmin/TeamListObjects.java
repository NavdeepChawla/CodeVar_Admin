package com.navdeep.codevaradmin;

import java.net.URL;

public class TeamListObjects
{
    String UserName;

    public TeamListObjects(String userName, URL userImageUrl) {
        UserName = userName;
        UserImageUrl = userImageUrl;
    }

    URL UserImageUrl;
}
