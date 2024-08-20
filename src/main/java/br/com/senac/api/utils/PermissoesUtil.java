package br.com.senac.api.utils;

import java.util.ArrayList;
import java.util.List;

public class PermissoesUtil {
    public static String addPermissoesRota(String[] permissoes) {
        String permisoesResult = "'ROLE_ADMIN'";

        for(int i=0; i < permissoes.length; i++){
            permisoesResult += ",'ROLE_" + permissoes[i] + "'";
        }

        return "hasAnyRole(" + permisoesResult + ")";
    }
}
