package pt.ipg.listadecompras;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ListasContentProvider  extends ContentProvider {

    public static final String AUTHORITY = "pt.ipg.listadecompras";
    public static final String CATEGORIAS = "categorias";
    public static final String LISTAS = "listas";
    public static final String PRODUTOS = "produtos";

    public static final int URI_CATEGORIAS = 100;
    public static final int URI_UNICA_CATEGORIA = 101;
    public static final int URI_LISTAS = 200;
    public static final int URI_UNICA_LISTA = 201;
    public static final int URI_PRODUTOS = 300;
    public static final int URI_UNICO_PRODUTO = 301;

    private SqliteOpenHelper bdListasOpenHelper;

    private UriMatcher getUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(AUTHORITY, CATEGORIAS, URI_CATEGORIAS);
        uriMatcher.addURI(AUTHORITY, CATEGORIAS + "/#", URI_UNICA_CATEGORIA);
        uriMatcher.addURI(AUTHORITY, LISTAS, URI_LISTAS);
        uriMatcher.addURI(AUTHORITY, LISTAS + "/#", URI_UNICA_LISTA);
        uriMatcher.addURI(AUTHORITY, PRODUTOS, URI_PRODUTOS);
        uriMatcher.addURI(AUTHORITY, PRODUTOS+ "/#", URI_UNICO_PRODUTO);

        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        bdListasOpenHelper = new SqliteOpenHelper(getContext());


        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase bd = bdListasOpenHelper.getReadableDatabase();

        String id = uri.getLastPathSegment();

        switch (getUriMatcher().match(uri)) {
            case URI_CATEGORIAS:
                return new BdTableCategorias(bd).query(projection, selection, selectionArgs, null, null, sortOrder);

            case URI_UNICA_CATEGORIA:
                return new BdTableCategorias(bd).query(projection, BdTableCategorias._ID + "=?", new String[] { id }, null, null, null);

            case URI_LISTAS:
                return new BdTableListas(bd).query(projection, selection, selectionArgs, null, null, sortOrder);

            case URI_UNICA_LISTA:
                return  new BdTableListas(bd).query(projection, BdTableListas._ID + "=?", new String[] { id }, null, null, null);
            case URI_PRODUTOS:
                return new BdTableProdutos(bd).query(projection, selection, selectionArgs, null, null, sortOrder);

            case URI_UNICO_PRODUTO:
                return  new BdTableProdutos(bd).query(projection, BdTableProdutos._ID + "=?", new String[] { id }, null, null, null);

        }

        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
