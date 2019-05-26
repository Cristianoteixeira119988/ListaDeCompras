package pt.ipg.listadecompras;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ListasContentProvider  extends ContentProvider {

    public static final String AUTHORITY = "pt.ipg.listadecompras";
    public static final String CATEGORIAS = "categorias";
    public static final String LISTAS = "listas";
    public static final String PRODUTOS = "produtos";


    private static final Uri ENDERECO_BASE = Uri.parse("content://" + AUTHORITY);
    public static final Uri ENDERECO_CATEGORIAS = Uri.withAppendedPath(ENDERECO_BASE, CATEGORIAS);
    public static final Uri ENDERECO_LISTAS = Uri.withAppendedPath(ENDERECO_BASE, LISTAS);
    public static final Uri ENDERECO_PRODUTOS = Uri.withAppendedPath(ENDERECO_BASE, PRODUTOS);


    public static final int URI_CATEGORIAS = 100;
    public static final int URI_UNICA_CATEGORIA = 101;
    public static final int URI_LISTAS = 200;
    public static final int URI_UNICA_LISTA = 201;
    public static final int URI_PRODUTOS = 300;
    public static final int URI_UNICO_PRODUTO = 301;

    public static final String UNICO_ITEM = "vnd.android.cursor.item/";
    public static final String MULTIPLOS_ITEMS = "vnd.android.cursor.dir/";

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

                default:
                 throw new UnsupportedOperationException("URI inválida (QUERY): " + uri.toString());
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (getUriMatcher().match(uri)) {
            case URI_CATEGORIAS:
                return MULTIPLOS_ITEMS + CATEGORIAS;
            case URI_UNICA_CATEGORIA:
                return UNICO_ITEM + CATEGORIAS;
            case URI_LISTAS:
                return MULTIPLOS_ITEMS + LISTAS;
            case URI_UNICA_LISTA:
                return UNICO_ITEM + LISTAS;
            case URI_PRODUTOS:
                return MULTIPLOS_ITEMS + PRODUTOS;
            case URI_UNICO_PRODUTO:
                return UNICO_ITEM + PRODUTOS;
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase bd = bdListasOpenHelper.getWritableDatabase();

        long id = -1;

        switch (getUriMatcher().match(uri)) {
            case URI_CATEGORIAS:
                id = new BdTableCategorias(bd).insert(values);
                break;

            case URI_LISTAS:
                id = new BdTableListas(bd).insert(values);
                break;
            case URI_PRODUTOS:
                id = new BdTableProdutos(bd).insert(values);
                break;

            default:
                throw new UnsupportedOperationException("URI inválida (INSERT):" + uri.toString());
        }

        if (id == -1) {
            throw new SQLException("Não foi possível inserir o registo");
        }

        return Uri.withAppendedPath(uri, String.valueOf(id));
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase bd = bdListasOpenHelper.getWritableDatabase();

        String id = uri.getLastPathSegment();

        switch (getUriMatcher().match(uri)) {
            case URI_UNICA_CATEGORIA:
                return new BdTableCategorias(bd).delete( BdTableCategorias._ID + "=?", new String[] {id});
            case URI_UNICA_LISTA:
                return new BdTableListas(bd).delete(BdTableListas._ID + "=?", new String[] {id});
            case URI_UNICO_PRODUTO:
                return new BdTableProdutos(bd).delete(BdTableProdutos._ID + "=?", new String[] {id});

            default:
                throw new UnsupportedOperationException("URI inválida (DELETE): " + uri.toString());
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase bd = bdListasOpenHelper.getWritableDatabase();

        String id = uri.getLastPathSegment();

        switch (getUriMatcher().match(uri)) {
            case URI_UNICA_CATEGORIA:
                return new BdTableCategorias(bd).update(values, BdTableCategorias._ID + "=?", new String[] {id});
            case URI_UNICA_LISTA:
                return new BdTableListas(bd).update(values, BdTableListas._ID + "=?", new String[] {id});
            case URI_UNICO_PRODUTO:
                return new BdTableProdutos(bd).update(values, BdTableProdutos._ID + "=?", new String[] {id});
            default:
                throw new UnsupportedOperationException("URI inválida (UPDATE): " + uri.toString());
        }
    }
}
