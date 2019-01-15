class MethodsWrapper {

    /* Get user info on authorization */

    static String authorizeUser(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Main.db.authorizeUser(data[2], data[3]);
    }

    /* Get user's publications ids on authorization */

    static String getPublsOfUser(String[] data) {
        if (data.length != 2) {
            return "wrong args";
        }
        return Main.db.getPublsOfUser(data[0]);
    }

    /* Get tables */

    static String getUsers(String[] data) {
        if (data.length != 2) {
            return "wrong args";
        }
        return Main.db.getUsers(data[0]);
    }

    static String getPublications(String[] data) {
        if (data.length != 2) {
            return "wrong args";
        }
        return Main.db.getPublications();
    }

    static String getBooks(String[] data) {
        if (data.length != 2) {
            return "wrong args";
        }
        return Main.db.getBooks();
    }

    static String getDigests(String[] data) {
        if (data.length != 2) {
            return "wrong args";
        }
        return Main.db.getDigests();
    }

    static String getArticles(String[] data) {
        if (data.length != 2) {
            return "wrong args";
        }
        return Main.db.getArticles();
    }

    static String getTheses(String[] data) {
        if (data.length != 2) {
            return "wrong args";
        }
        return Main.db.getTheses();
    }

    static String getDocs(String[] data) {
        if (data.length != 2) {
            return "wrong args";
        }
        return Main.db.getDocs();
    }

    static String getSubjects(String[] data) {
        if (data.length != 2) {
            return "wrong args";
        }
        return Main.db.getSubjects();
    }

    static String getMagazines(String[] data) {
        if (data.length != 2) {
            return "wrong args";
        }
        return Main.db.getMagazines();
    }

    static String getAuthors(String[] data) {
        if (data.length != 2) {
            return "wrong args";
        }
        return Main.db.getAuthors();
    }

    static String getEditors(String[] data) {
        if (data.length != 2) {
            return "wrong args";
        }
        return Main.db.getEditors();
    }

    static String getOrganizations(String[] data) {
        if (data.length != 2) {
            return "wrong args";
        }
        return Main.db.getOrganizations();
    }

    static String getPublHouses(String[] data) {
        if (data.length != 2) {
            return "wrong args";
        }
        return Main.db.getPublHouses();
    }

    static String getKeywords(String[] data) {
        if (data.length != 2) {
            return "wrong args";
        }
        return Main.db.getKeywords();
    }

    static String getUdc(String[] data) {
        if (data.length != 2) {
            return "wrong args";
        }
        return Main.db.getUdc();
    }

    static String getVerfs(String[] data) {
        if (data.length != 2) {
            return "wrong args";
        }
        return Main.db.getVerfs(data[0]);
    }

    static String getAuthorsWorktimes(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Main.db.getAuthorsWorktimes(data[2]);
    }

    /* Get rows that references to publication */

    static String getAuthorsOfPubl(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Main.db.getAuthorsOfPubl(data[2]);
    }

    static String getEditorsOfPubl(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Main.db.getEditorsOfPubl(data[2]);
    }

    static String getKeywordsOfPubl(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Main.db.getKeywordsOfPubl(data[2]);
    }

    static String getUdcOfPubl(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Main.db.getUdcOfPubl(data[2]);
    }

    /* Add rows to tables */

    static String addUser(String[] data) {
        if (data.length != 12) {
            return "wrong args";
        }
        return Main.db.addUser(data[0], data[2], data[3], data[4], data[5], data[6], data[7], data[8],
                data[9], data[10], data[11]);
    }

    static String addBook(String[] data) {
        if (data.length != 5) {
            return "wrong args";
        }
        return Main.db.addBook(data[0], data[2], data[3], data[4]);
    }

    static String addDigest(String[] data) {
        if (data.length != 5) {
            return "wrong args";
        }
        return Main.db.addDigest(data[0], data[2], data[3], data[4]);
    }

    static String addMagazineArticle(String[] data) {
        if (data.length != 6) {
            return "wrong args";
        }
        return Main.db.addMagazineArticle(data[0], data[2], data[3], data[4], data[5]);
    }

    static String addDigestArticle(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Main.db.addDigestArticle(data[0], data[2], data[3]);
    }

    static String addMagazineTheses(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Main.db.addMagazineTheses(data[0], data[2], data[3]);
    }

    static String addDigestTheses(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Main.db.addDigestTheses(data[0], data[2], data[3]);
    }

    static String addDocs(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Main.db.addDocs(data[0], data[2], data[3]);
    }

    static String addSubject(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Main.db.addSubject(data[0], data[2]);
    }

    static String addMagazine(String[] data) {
        if (data.length != 5) {
            return "wrong args";
        }
        return Main.db.addMagazine(data[0], data[2], data[3], data[4]);
    }

    static String addAuthor(String[] data) {
        if (data.length != 10) {
            return "wrong args";
        }
        return Main.db.addAuthor(data[0], data[2], data[3], data[4], data[5], data[6], data[7], data[8],
                data[9]);
    }

    static String addEditor(String[] data) {
        if (data.length != 10) {
            return "wrong args";
        }
        return Main.db.addEditor(data[0], data[2], data[3], data[4], data[5], data[6], data[7], data[8],
                data[9]);
    }

    static String addOrganization(String[] data) {
        if (data.length != 6) {
            return "wrong args";
        }
        return Main.db.addOrganization(data[0], data[2], data[3], data[4], data[5]);
    }

    static String addPublHouse(String[] data) {
        if (data.length != 6) {
            return "wrong args";
        }
        return Main.db.addPublHouse(data[0], data[2], data[3], data[4], data[5]);
    }

    static String addKeyword(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Main.db.addKeyword(data[0], data[2]);
    }

    static String addUdc(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Main.db.addUdc(data[0], data[2]);
    }

    static String addVerf(String[] data) {
        if (data.length != 5) {
            return "wrong args";
        }
        return Main.db.addVerf(data[0], data[2], data[3], data[4]);
    }

    static String addAuthorWorktime(String[] data) {
        if (data.length != 6) {
            return "wrong args";
        }
        return Main.db.addAuthorWorktime(data[0], data[2], data[3], data[4], data[5]);
    }

    /* Add references to publication */

    static String addAuthorToPubl(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Main.db.addAuthorToPubl(data[0], data[2], data[3]);
    }

    static String addEditorToPubl(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Main.db.addEditorToPubl(data[0], data[2], data[3]);
    }

    static String addKeywordToPubl(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Main.db.addKeywordToPubl(data[0], data[2], data[3]);
    }

    static String addUdcToPubl(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Main.db.addUdcToPubl(data[0], data[2], data[3]);
    }

    /* Accept verification */

    static String addUserToAuthor(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Main.db.addUserToAuthor(data[0], data[2], data[3]);
    }

    static String addUserToPublHouse(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Main.db.addUserToPublHouse(data[0], data[2], data[3]);
    }

    /* Delete from tables */

    static String deleteUser(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Main.db.deleteUser(data[0], data[2]);
    }

    static String deletePublication(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Main.db.deletePublication(data[0], data[2]);
    }

    static String deleteSubject(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Main.db.deleteSubject(data[0], data[2]);
    }

    static String deleteMagazine(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Main.db.deleteMagazine(data[0], data[2]);
    }

    static String deleteAuthor(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Main.db.deleteAuthor(data[0], data[2]);
    }

    static String deleteEditor(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Main.db.deleteEditor(data[0], data[2]);
    }

    static String deleteOrganization(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Main.db.deleteOrganization(data[0], data[2]);
    }

    static String deletePublHouse(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Main.db.deletePublHouse(data[0], data[2]);
    }

    static String deleteKeyword(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Main.db.deleteKeyword(data[0], data[2]);
    }

    static String deleteUdc(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Main.db.deleteUdc(data[0], data[2]);
    }

    static String deleteVerf(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Main.db.deleteVerf(data[0], data[2]);
    }

    static String deleteAuthorWorktime(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Main.db.deleteAuthorWorktime(data[0], data[2]);
    }

    /* Remove reference from publication */

    static String deleteAuthorFromPubl(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Main.db.deleteAuthorFromPubl(data[0], data[2], data[3]);
    }

    static String deleteEditorFromPubl(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Main.db.deleteEditorFromPubl(data[0], data[2], data[3]);
    }

    static String deleteKeywordFromPubl(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Main.db.deleteKeywordFromPubl(data[0], data[2], data[3]);
    }

    static String deleteUdcFromPubl(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Main.db.deleteUdcFromPubl(data[0], data[2], data[3]);
    }

    /* Changing */

    static String changeUserInfo(String[] data) {
        if (data.length != 10) {
            return "wrong args";
        }
        return Main.db.changeUserInfo(data[0], data[2], data[3], data[4], data[5], data[6], data[7], data[8],
                data[9]);
    }

    static String changeUserPassword(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Main.db.changeUserPassword(data[0], data[2], data[3]);
    }

    static String changeBook(String[] data) {
        if (data.length != 5) {
            return "wrong args";
        }
        return Main.db.changeBook(data[0], data[2], data[3], data[4]);
    }

    static String changeDigest(String[] data) {
        if (data.length != 5) {
            return "wrong args";
        }
        return Main.db.changeDigest(data[0], data[2], data[3], data[4]);
    }

    static String changeArticle(String[] data) {
        if (data.length != 6) {
            return "wrong args";
        }
        return Main.db.changeArticle(data[0], data[2], data[3], data[4], data[5]);
    }

    static String changeTheses(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Main.db.changeTheses(data[0], data[2], data[3]);
    }

    static String changeDocs(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Main.db.changeDocs(data[0], data[2], data[3]);
    }

    static String changeSubject(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Main.db.changeSubject(data[0], data[2], data[3]);
    }

    static String changeMagazine(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Main.db.changeMagazine(data[0], data[2], data[3]);
    }

    static String changeOrganization(String[] data) {
        if (data.length != 7) {
            return "wrong args";
        }
        return Main.db.changeOrganization(data[0], data[2], data[3], data[4], data[5], data[6]);
    }

    static String changePublHouse(String[] data) {
        if (data.length != 7) {
            return "wrong args";
        }
        return Main.db.changePublHouse(data[0], data[2], data[3], data[4], data[5], data[6]);
    }

    static String changeAuthor(String[] data) {
        if (data.length != 11) {
            return "wrong args";
        }
        return Main.db.changeAuthor(data[0], data[2], data[3], data[4], data[5], data[6], data[7], data[8],
                data[9], data[10]);
    }

    static String changeEditor(String[] data) {
        if (data.length != 11) {
            return "wrong args";
        }
        return Main.db.changeEditor(data[0], data[2], data[3], data[4], data[5], data[6], data[7], data[8],
                data[9], data[10]);
    }
}
