package com.izk.mvc.model;

import java.util.List;

/**
 * Created by xzhang.
 */

public class Repo {


    /**
     * contributors : [{"avatar":"https://avatars3.githubusercontent.com/u/14830574?v=3&s=40","name":"ImmortalZ"}]
     * des : This is a simple util to create Activity transition animation
     * href : /ImmortalZ/TransitionHelper
     * language : Java
     * link : https://github.com/ImmortalZ/TransitionHelper
     * meta : 209 stars today
     * name : TransitionHelper
     * owner : ImmortalZ
     */

    private String des;
    private String href;
    private String language;
    private String link;
    private String meta;
    private String name;
    private String owner;
    private List<ContributorsBean> contributors;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<ContributorsBean> getContributors() {
        return contributors;
    }

    public void setContributors(List<ContributorsBean> contributors) {
        this.contributors = contributors;
    }

    public static class ContributorsBean {
        /**
         * avatar : https://avatars3.githubusercontent.com/u/14830574?v=3&s=40
         * name : ImmortalZ
         */

        private String avatar;
        private String name;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
