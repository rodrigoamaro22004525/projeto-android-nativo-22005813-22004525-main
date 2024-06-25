package pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local;

import android.database.Cursor;
import androidx.collection.ArrayMap;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local.relations.FilmeWithPhotos;
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local.relations.InfoGenreCrossRef;
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local.relations.InfoWithFilmes;
import pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local.relations.InfoWithGenres;

@SuppressWarnings({"unchecked", "deprecation"})
public final class FilmeDao_Impl implements FilmeDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<FilmeDB> __insertionAdapterOfFilmeDB;

  private final EntityInsertionAdapter<PhotoDB> __insertionAdapterOfPhotoDB;

  private final EntityInsertionAdapter<InfoDB> __insertionAdapterOfInfoDB;

  private final EntityInsertionAdapter<GenreDB> __insertionAdapterOfGenreDB;

  private final EntityInsertionAdapter<InfoGenreCrossRef> __insertionAdapterOfInfoGenreCrossRef;

  public FilmeDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFilmeDB = new EntityInsertionAdapter<FilmeDB>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `filmes` (`idFilme`,`idCinema`,`linkInfo`,`avaliacaoUtilizador`,`data`,`observacoes`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FilmeDB value) {
        if (value.getIdFilme() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getIdFilme());
        }
        stmt.bindLong(2, value.getIdCinema());
        if (value.getLinkInfo() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getLinkInfo());
        }
        stmt.bindLong(4, value.getAvaliacaoUtilizador());
        stmt.bindLong(5, value.getData());
        if (value.getObservacoes() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getObservacoes());
        }
      }
    };
    this.__insertionAdapterOfPhotoDB = new EntityInsertionAdapter<PhotoDB>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `photos` (`photoStr`,`idFilme`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PhotoDB value) {
        if (value.getPhotoStr() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getPhotoStr());
        }
        if (value.getIdFilme() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getIdFilme());
        }
      }
    };
    this.__insertionAdapterOfInfoDB = new EntityInsertionAdapter<InfoDB>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `infos` (`linkInfo`,`name`,`imagemCartaz`,`sinopse`,`dataLancamento`,`avaliacaoIMDB`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, InfoDB value) {
        if (value.getLinkInfo() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getLinkInfo());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getImagemCartaz() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getImagemCartaz());
        }
        if (value.getSinopse() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getSinopse());
        }
        stmt.bindLong(5, value.getDataLancamento());
        stmt.bindDouble(6, value.getAvaliacaoIMDB());
      }
    };
    this.__insertionAdapterOfGenreDB = new EntityInsertionAdapter<GenreDB>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `genres` (`nameGenre`) VALUES (?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, GenreDB value) {
        if (value.getNameGenre() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getNameGenre());
        }
      }
    };
    this.__insertionAdapterOfInfoGenreCrossRef = new EntityInsertionAdapter<InfoGenreCrossRef>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `InfoGenreCrossRef` (`linkInfo`,`nameGenre`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, InfoGenreCrossRef value) {
        if (value.getLinkInfo() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getLinkInfo());
        }
        if (value.getNameGenre() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNameGenre());
        }
      }
    };
  }

  @Override
  public void insertFilme(final FilmeDB filme) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfFilmeDB.insert(filme);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertPhotos(final PhotoDB photo) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfPhotoDB.insert(photo);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertInfo(final InfoDB info) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfInfoDB.insert(info);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertGenre(final GenreDB genre) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfGenreDB.insert(genre);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertInfoGenreCrossRef(final InfoGenreCrossRef crossRef) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfInfoGenreCrossRef.insert(crossRef);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public InfoDB getInfo(final String linkInfo) {
    final String _sql = "SELECT * FROM infos WHERE linkInfo = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (linkInfo == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, linkInfo);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfLinkInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "linkInfo");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfImagemCartaz = CursorUtil.getColumnIndexOrThrow(_cursor, "imagemCartaz");
      final int _cursorIndexOfSinopse = CursorUtil.getColumnIndexOrThrow(_cursor, "sinopse");
      final int _cursorIndexOfDataLancamento = CursorUtil.getColumnIndexOrThrow(_cursor, "dataLancamento");
      final int _cursorIndexOfAvaliacaoIMDB = CursorUtil.getColumnIndexOrThrow(_cursor, "avaliacaoIMDB");
      final InfoDB _result;
      if(_cursor.moveToFirst()) {
        final String _tmpLinkInfo;
        if (_cursor.isNull(_cursorIndexOfLinkInfo)) {
          _tmpLinkInfo = null;
        } else {
          _tmpLinkInfo = _cursor.getString(_cursorIndexOfLinkInfo);
        }
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpImagemCartaz;
        if (_cursor.isNull(_cursorIndexOfImagemCartaz)) {
          _tmpImagemCartaz = null;
        } else {
          _tmpImagemCartaz = _cursor.getString(_cursorIndexOfImagemCartaz);
        }
        final String _tmpSinopse;
        if (_cursor.isNull(_cursorIndexOfSinopse)) {
          _tmpSinopse = null;
        } else {
          _tmpSinopse = _cursor.getString(_cursorIndexOfSinopse);
        }
        final long _tmpDataLancamento;
        _tmpDataLancamento = _cursor.getLong(_cursorIndexOfDataLancamento);
        final double _tmpAvaliacaoIMDB;
        _tmpAvaliacaoIMDB = _cursor.getDouble(_cursorIndexOfAvaliacaoIMDB);
        _result = new InfoDB(_tmpLinkInfo,_tmpName,_tmpImagemCartaz,_tmpSinopse,_tmpDataLancamento,_tmpAvaliacaoIMDB);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public FilmeDB getFilmeById(final String idFilme) {
    final String _sql = "SELECT * FROM filmes JOIN infos USING (linkInfo) WHERE idFilme = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (idFilme == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, idFilme);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfIdFilme = CursorUtil.getColumnIndexOrThrow(_cursor, "idFilme");
      final int _cursorIndexOfIdCinema = CursorUtil.getColumnIndexOrThrow(_cursor, "idCinema");
      final int _cursorIndexOfLinkInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "linkInfo");
      final int _cursorIndexOfAvaliacaoUtilizador = CursorUtil.getColumnIndexOrThrow(_cursor, "avaliacaoUtilizador");
      final int _cursorIndexOfData = CursorUtil.getColumnIndexOrThrow(_cursor, "data");
      final int _cursorIndexOfObservacoes = CursorUtil.getColumnIndexOrThrow(_cursor, "observacoes");
      final FilmeDB _result;
      if(_cursor.moveToFirst()) {
        final String _tmpIdFilme;
        if (_cursor.isNull(_cursorIndexOfIdFilme)) {
          _tmpIdFilme = null;
        } else {
          _tmpIdFilme = _cursor.getString(_cursorIndexOfIdFilme);
        }
        final int _tmpIdCinema;
        _tmpIdCinema = _cursor.getInt(_cursorIndexOfIdCinema);
        final String _tmpLinkInfo;
        if (_cursor.isNull(_cursorIndexOfLinkInfo)) {
          _tmpLinkInfo = null;
        } else {
          _tmpLinkInfo = _cursor.getString(_cursorIndexOfLinkInfo);
        }
        final int _tmpAvaliacaoUtilizador;
        _tmpAvaliacaoUtilizador = _cursor.getInt(_cursorIndexOfAvaliacaoUtilizador);
        final long _tmpData;
        _tmpData = _cursor.getLong(_cursorIndexOfData);
        final String _tmpObservacoes;
        if (_cursor.isNull(_cursorIndexOfObservacoes)) {
          _tmpObservacoes = null;
        } else {
          _tmpObservacoes = _cursor.getString(_cursorIndexOfObservacoes);
        }
        _result = new FilmeDB(_tmpIdFilme,_tmpIdCinema,_tmpLinkInfo,_tmpAvaliacaoUtilizador,_tmpData,_tmpObservacoes);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public FilmeDB getFilmeByTitle(final String title) {
    final String _sql = "SELECT * FROM filmes JOIN infos USING (linkInfo) WHERE name = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (title == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, title);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfIdFilme = CursorUtil.getColumnIndexOrThrow(_cursor, "idFilme");
      final int _cursorIndexOfIdCinema = CursorUtil.getColumnIndexOrThrow(_cursor, "idCinema");
      final int _cursorIndexOfLinkInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "linkInfo");
      final int _cursorIndexOfAvaliacaoUtilizador = CursorUtil.getColumnIndexOrThrow(_cursor, "avaliacaoUtilizador");
      final int _cursorIndexOfData = CursorUtil.getColumnIndexOrThrow(_cursor, "data");
      final int _cursorIndexOfObservacoes = CursorUtil.getColumnIndexOrThrow(_cursor, "observacoes");
      final FilmeDB _result;
      if(_cursor.moveToFirst()) {
        final String _tmpIdFilme;
        if (_cursor.isNull(_cursorIndexOfIdFilme)) {
          _tmpIdFilme = null;
        } else {
          _tmpIdFilme = _cursor.getString(_cursorIndexOfIdFilme);
        }
        final int _tmpIdCinema;
        _tmpIdCinema = _cursor.getInt(_cursorIndexOfIdCinema);
        final String _tmpLinkInfo;
        if (_cursor.isNull(_cursorIndexOfLinkInfo)) {
          _tmpLinkInfo = null;
        } else {
          _tmpLinkInfo = _cursor.getString(_cursorIndexOfLinkInfo);
        }
        final int _tmpAvaliacaoUtilizador;
        _tmpAvaliacaoUtilizador = _cursor.getInt(_cursorIndexOfAvaliacaoUtilizador);
        final long _tmpData;
        _tmpData = _cursor.getLong(_cursorIndexOfData);
        final String _tmpObservacoes;
        if (_cursor.isNull(_cursorIndexOfObservacoes)) {
          _tmpObservacoes = null;
        } else {
          _tmpObservacoes = _cursor.getString(_cursorIndexOfObservacoes);
        }
        _result = new FilmeDB(_tmpIdFilme,_tmpIdCinema,_tmpLinkInfo,_tmpAvaliacaoUtilizador,_tmpData,_tmpObservacoes);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<InfoWithFilmes> getInfosWithFilme() {
    final String _sql = "SELECT * FROM infos";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
      try {
        final int _cursorIndexOfLinkInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "linkInfo");
        final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
        final int _cursorIndexOfImagemCartaz = CursorUtil.getColumnIndexOrThrow(_cursor, "imagemCartaz");
        final int _cursorIndexOfSinopse = CursorUtil.getColumnIndexOrThrow(_cursor, "sinopse");
        final int _cursorIndexOfDataLancamento = CursorUtil.getColumnIndexOrThrow(_cursor, "dataLancamento");
        final int _cursorIndexOfAvaliacaoIMDB = CursorUtil.getColumnIndexOrThrow(_cursor, "avaliacaoIMDB");
        final ArrayMap<String, ArrayList<FilmeDB>> _collectionFilmes = new ArrayMap<String, ArrayList<FilmeDB>>();
        while (_cursor.moveToNext()) {
          final String _tmpKey = _cursor.getString(_cursorIndexOfLinkInfo);
          ArrayList<FilmeDB> _tmpFilmesCollection = _collectionFilmes.get(_tmpKey);
          if (_tmpFilmesCollection == null) {
            _tmpFilmesCollection = new ArrayList<FilmeDB>();
            _collectionFilmes.put(_tmpKey, _tmpFilmesCollection);
          }
        }
        _cursor.moveToPosition(-1);
        __fetchRelationshipfilmesAsptUlusofonaDeisiCm2223G2200581322004525DataLocalFilmeDB(_collectionFilmes);
        final List<InfoWithFilmes> _result = new ArrayList<InfoWithFilmes>(_cursor.getCount());
        while(_cursor.moveToNext()) {
          final InfoWithFilmes _item;
          final InfoDB _tmpInfo;
          final String _tmpLinkInfo;
          if (_cursor.isNull(_cursorIndexOfLinkInfo)) {
            _tmpLinkInfo = null;
          } else {
            _tmpLinkInfo = _cursor.getString(_cursorIndexOfLinkInfo);
          }
          final String _tmpName;
          if (_cursor.isNull(_cursorIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _cursor.getString(_cursorIndexOfName);
          }
          final String _tmpImagemCartaz;
          if (_cursor.isNull(_cursorIndexOfImagemCartaz)) {
            _tmpImagemCartaz = null;
          } else {
            _tmpImagemCartaz = _cursor.getString(_cursorIndexOfImagemCartaz);
          }
          final String _tmpSinopse;
          if (_cursor.isNull(_cursorIndexOfSinopse)) {
            _tmpSinopse = null;
          } else {
            _tmpSinopse = _cursor.getString(_cursorIndexOfSinopse);
          }
          final long _tmpDataLancamento;
          _tmpDataLancamento = _cursor.getLong(_cursorIndexOfDataLancamento);
          final double _tmpAvaliacaoIMDB;
          _tmpAvaliacaoIMDB = _cursor.getDouble(_cursorIndexOfAvaliacaoIMDB);
          _tmpInfo = new InfoDB(_tmpLinkInfo,_tmpName,_tmpImagemCartaz,_tmpSinopse,_tmpDataLancamento,_tmpAvaliacaoIMDB);
          ArrayList<FilmeDB> _tmpFilmesCollection_1 = null;
          final String _tmpKey_1 = _cursor.getString(_cursorIndexOfLinkInfo);
          _tmpFilmesCollection_1 = _collectionFilmes.get(_tmpKey_1);
          if (_tmpFilmesCollection_1 == null) {
            _tmpFilmesCollection_1 = new ArrayList<FilmeDB>();
          }
          _item = new InfoWithFilmes(_tmpInfo,_tmpFilmesCollection_1);
          _result.add(_item);
        }
        __db.setTransactionSuccessful();
        return _result;
      } finally {
        _cursor.close();
        _statement.release();
      }
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<InfoWithGenres> getInfosWithGenres() {
    final String _sql = "SELECT * FROM infos";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
      try {
        final int _cursorIndexOfLinkInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "linkInfo");
        final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
        final int _cursorIndexOfImagemCartaz = CursorUtil.getColumnIndexOrThrow(_cursor, "imagemCartaz");
        final int _cursorIndexOfSinopse = CursorUtil.getColumnIndexOrThrow(_cursor, "sinopse");
        final int _cursorIndexOfDataLancamento = CursorUtil.getColumnIndexOrThrow(_cursor, "dataLancamento");
        final int _cursorIndexOfAvaliacaoIMDB = CursorUtil.getColumnIndexOrThrow(_cursor, "avaliacaoIMDB");
        final ArrayMap<String, ArrayList<GenreDB>> _collectionGenres = new ArrayMap<String, ArrayList<GenreDB>>();
        while (_cursor.moveToNext()) {
          final String _tmpKey = _cursor.getString(_cursorIndexOfLinkInfo);
          ArrayList<GenreDB> _tmpGenresCollection = _collectionGenres.get(_tmpKey);
          if (_tmpGenresCollection == null) {
            _tmpGenresCollection = new ArrayList<GenreDB>();
            _collectionGenres.put(_tmpKey, _tmpGenresCollection);
          }
        }
        _cursor.moveToPosition(-1);
        __fetchRelationshipgenresAsptUlusofonaDeisiCm2223G2200581322004525DataLocalGenreDB(_collectionGenres);
        final List<InfoWithGenres> _result = new ArrayList<InfoWithGenres>(_cursor.getCount());
        while(_cursor.moveToNext()) {
          final InfoWithGenres _item;
          final InfoDB _tmpInfo;
          final String _tmpLinkInfo;
          if (_cursor.isNull(_cursorIndexOfLinkInfo)) {
            _tmpLinkInfo = null;
          } else {
            _tmpLinkInfo = _cursor.getString(_cursorIndexOfLinkInfo);
          }
          final String _tmpName;
          if (_cursor.isNull(_cursorIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _cursor.getString(_cursorIndexOfName);
          }
          final String _tmpImagemCartaz;
          if (_cursor.isNull(_cursorIndexOfImagemCartaz)) {
            _tmpImagemCartaz = null;
          } else {
            _tmpImagemCartaz = _cursor.getString(_cursorIndexOfImagemCartaz);
          }
          final String _tmpSinopse;
          if (_cursor.isNull(_cursorIndexOfSinopse)) {
            _tmpSinopse = null;
          } else {
            _tmpSinopse = _cursor.getString(_cursorIndexOfSinopse);
          }
          final long _tmpDataLancamento;
          _tmpDataLancamento = _cursor.getLong(_cursorIndexOfDataLancamento);
          final double _tmpAvaliacaoIMDB;
          _tmpAvaliacaoIMDB = _cursor.getDouble(_cursorIndexOfAvaliacaoIMDB);
          _tmpInfo = new InfoDB(_tmpLinkInfo,_tmpName,_tmpImagemCartaz,_tmpSinopse,_tmpDataLancamento,_tmpAvaliacaoIMDB);
          ArrayList<GenreDB> _tmpGenresCollection_1 = null;
          final String _tmpKey_1 = _cursor.getString(_cursorIndexOfLinkInfo);
          _tmpGenresCollection_1 = _collectionGenres.get(_tmpKey_1);
          if (_tmpGenresCollection_1 == null) {
            _tmpGenresCollection_1 = new ArrayList<GenreDB>();
          }
          _item = new InfoWithGenres(_tmpInfo,_tmpGenresCollection_1);
          _result.add(_item);
        }
        __db.setTransactionSuccessful();
        return _result;
      } finally {
        _cursor.close();
        _statement.release();
      }
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<FilmeWithPhotos> getFilmeWithPhotos(final String idFilme) {
    final String _sql = "SELECT * FROM filmes WHERE idFilme = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (idFilme == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, idFilme);
    }
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
      try {
        final int _cursorIndexOfIdFilme = CursorUtil.getColumnIndexOrThrow(_cursor, "idFilme");
        final int _cursorIndexOfIdCinema = CursorUtil.getColumnIndexOrThrow(_cursor, "idCinema");
        final int _cursorIndexOfLinkInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "linkInfo");
        final int _cursorIndexOfAvaliacaoUtilizador = CursorUtil.getColumnIndexOrThrow(_cursor, "avaliacaoUtilizador");
        final int _cursorIndexOfData = CursorUtil.getColumnIndexOrThrow(_cursor, "data");
        final int _cursorIndexOfObservacoes = CursorUtil.getColumnIndexOrThrow(_cursor, "observacoes");
        final ArrayMap<String, ArrayList<PhotoDB>> _collectionPhotos = new ArrayMap<String, ArrayList<PhotoDB>>();
        while (_cursor.moveToNext()) {
          final String _tmpKey = _cursor.getString(_cursorIndexOfIdFilme);
          ArrayList<PhotoDB> _tmpPhotosCollection = _collectionPhotos.get(_tmpKey);
          if (_tmpPhotosCollection == null) {
            _tmpPhotosCollection = new ArrayList<PhotoDB>();
            _collectionPhotos.put(_tmpKey, _tmpPhotosCollection);
          }
        }
        _cursor.moveToPosition(-1);
        __fetchRelationshipphotosAsptUlusofonaDeisiCm2223G2200581322004525DataLocalPhotoDB(_collectionPhotos);
        final List<FilmeWithPhotos> _result = new ArrayList<FilmeWithPhotos>(_cursor.getCount());
        while(_cursor.moveToNext()) {
          final FilmeWithPhotos _item;
          final FilmeDB _tmpFilme;
          final String _tmpIdFilme;
          if (_cursor.isNull(_cursorIndexOfIdFilme)) {
            _tmpIdFilme = null;
          } else {
            _tmpIdFilme = _cursor.getString(_cursorIndexOfIdFilme);
          }
          final int _tmpIdCinema;
          _tmpIdCinema = _cursor.getInt(_cursorIndexOfIdCinema);
          final String _tmpLinkInfo;
          if (_cursor.isNull(_cursorIndexOfLinkInfo)) {
            _tmpLinkInfo = null;
          } else {
            _tmpLinkInfo = _cursor.getString(_cursorIndexOfLinkInfo);
          }
          final int _tmpAvaliacaoUtilizador;
          _tmpAvaliacaoUtilizador = _cursor.getInt(_cursorIndexOfAvaliacaoUtilizador);
          final long _tmpData;
          _tmpData = _cursor.getLong(_cursorIndexOfData);
          final String _tmpObservacoes;
          if (_cursor.isNull(_cursorIndexOfObservacoes)) {
            _tmpObservacoes = null;
          } else {
            _tmpObservacoes = _cursor.getString(_cursorIndexOfObservacoes);
          }
          _tmpFilme = new FilmeDB(_tmpIdFilme,_tmpIdCinema,_tmpLinkInfo,_tmpAvaliacaoUtilizador,_tmpData,_tmpObservacoes);
          ArrayList<PhotoDB> _tmpPhotosCollection_1 = null;
          final String _tmpKey_1 = _cursor.getString(_cursorIndexOfIdFilme);
          _tmpPhotosCollection_1 = _collectionPhotos.get(_tmpKey_1);
          if (_tmpPhotosCollection_1 == null) {
            _tmpPhotosCollection_1 = new ArrayList<PhotoDB>();
          }
          _item = new FilmeWithPhotos(_tmpFilme,_tmpPhotosCollection_1);
          _result.add(_item);
        }
        __db.setTransactionSuccessful();
        return _result;
      } finally {
        _cursor.close();
        _statement.release();
      }
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<InfoWithFilmes> getInfoWithFilmes(final String linkInfo) {
    final String _sql = "SELECT * FROM infos WHERE linkInfo = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (linkInfo == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, linkInfo);
    }
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
      try {
        final int _cursorIndexOfLinkInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "linkInfo");
        final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
        final int _cursorIndexOfImagemCartaz = CursorUtil.getColumnIndexOrThrow(_cursor, "imagemCartaz");
        final int _cursorIndexOfSinopse = CursorUtil.getColumnIndexOrThrow(_cursor, "sinopse");
        final int _cursorIndexOfDataLancamento = CursorUtil.getColumnIndexOrThrow(_cursor, "dataLancamento");
        final int _cursorIndexOfAvaliacaoIMDB = CursorUtil.getColumnIndexOrThrow(_cursor, "avaliacaoIMDB");
        final ArrayMap<String, ArrayList<FilmeDB>> _collectionFilmes = new ArrayMap<String, ArrayList<FilmeDB>>();
        while (_cursor.moveToNext()) {
          final String _tmpKey = _cursor.getString(_cursorIndexOfLinkInfo);
          ArrayList<FilmeDB> _tmpFilmesCollection = _collectionFilmes.get(_tmpKey);
          if (_tmpFilmesCollection == null) {
            _tmpFilmesCollection = new ArrayList<FilmeDB>();
            _collectionFilmes.put(_tmpKey, _tmpFilmesCollection);
          }
        }
        _cursor.moveToPosition(-1);
        __fetchRelationshipfilmesAsptUlusofonaDeisiCm2223G2200581322004525DataLocalFilmeDB(_collectionFilmes);
        final List<InfoWithFilmes> _result = new ArrayList<InfoWithFilmes>(_cursor.getCount());
        while(_cursor.moveToNext()) {
          final InfoWithFilmes _item;
          final InfoDB _tmpInfo;
          final String _tmpLinkInfo;
          if (_cursor.isNull(_cursorIndexOfLinkInfo)) {
            _tmpLinkInfo = null;
          } else {
            _tmpLinkInfo = _cursor.getString(_cursorIndexOfLinkInfo);
          }
          final String _tmpName;
          if (_cursor.isNull(_cursorIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _cursor.getString(_cursorIndexOfName);
          }
          final String _tmpImagemCartaz;
          if (_cursor.isNull(_cursorIndexOfImagemCartaz)) {
            _tmpImagemCartaz = null;
          } else {
            _tmpImagemCartaz = _cursor.getString(_cursorIndexOfImagemCartaz);
          }
          final String _tmpSinopse;
          if (_cursor.isNull(_cursorIndexOfSinopse)) {
            _tmpSinopse = null;
          } else {
            _tmpSinopse = _cursor.getString(_cursorIndexOfSinopse);
          }
          final long _tmpDataLancamento;
          _tmpDataLancamento = _cursor.getLong(_cursorIndexOfDataLancamento);
          final double _tmpAvaliacaoIMDB;
          _tmpAvaliacaoIMDB = _cursor.getDouble(_cursorIndexOfAvaliacaoIMDB);
          _tmpInfo = new InfoDB(_tmpLinkInfo,_tmpName,_tmpImagemCartaz,_tmpSinopse,_tmpDataLancamento,_tmpAvaliacaoIMDB);
          ArrayList<FilmeDB> _tmpFilmesCollection_1 = null;
          final String _tmpKey_1 = _cursor.getString(_cursorIndexOfLinkInfo);
          _tmpFilmesCollection_1 = _collectionFilmes.get(_tmpKey_1);
          if (_tmpFilmesCollection_1 == null) {
            _tmpFilmesCollection_1 = new ArrayList<FilmeDB>();
          }
          _item = new InfoWithFilmes(_tmpInfo,_tmpFilmesCollection_1);
          _result.add(_item);
        }
        __db.setTransactionSuccessful();
        return _result;
      } finally {
        _cursor.close();
        _statement.release();
      }
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<InfoWithGenres> getInfoWithGenres(final String linkInfo) {
    final String _sql = "SELECT * FROM infos WHERE linkInfo = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (linkInfo == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, linkInfo);
    }
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
      try {
        final int _cursorIndexOfLinkInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "linkInfo");
        final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
        final int _cursorIndexOfImagemCartaz = CursorUtil.getColumnIndexOrThrow(_cursor, "imagemCartaz");
        final int _cursorIndexOfSinopse = CursorUtil.getColumnIndexOrThrow(_cursor, "sinopse");
        final int _cursorIndexOfDataLancamento = CursorUtil.getColumnIndexOrThrow(_cursor, "dataLancamento");
        final int _cursorIndexOfAvaliacaoIMDB = CursorUtil.getColumnIndexOrThrow(_cursor, "avaliacaoIMDB");
        final ArrayMap<String, ArrayList<GenreDB>> _collectionGenres = new ArrayMap<String, ArrayList<GenreDB>>();
        while (_cursor.moveToNext()) {
          final String _tmpKey = _cursor.getString(_cursorIndexOfLinkInfo);
          ArrayList<GenreDB> _tmpGenresCollection = _collectionGenres.get(_tmpKey);
          if (_tmpGenresCollection == null) {
            _tmpGenresCollection = new ArrayList<GenreDB>();
            _collectionGenres.put(_tmpKey, _tmpGenresCollection);
          }
        }
        _cursor.moveToPosition(-1);
        __fetchRelationshipgenresAsptUlusofonaDeisiCm2223G2200581322004525DataLocalGenreDB(_collectionGenres);
        final List<InfoWithGenres> _result = new ArrayList<InfoWithGenres>(_cursor.getCount());
        while(_cursor.moveToNext()) {
          final InfoWithGenres _item;
          final InfoDB _tmpInfo;
          final String _tmpLinkInfo;
          if (_cursor.isNull(_cursorIndexOfLinkInfo)) {
            _tmpLinkInfo = null;
          } else {
            _tmpLinkInfo = _cursor.getString(_cursorIndexOfLinkInfo);
          }
          final String _tmpName;
          if (_cursor.isNull(_cursorIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _cursor.getString(_cursorIndexOfName);
          }
          final String _tmpImagemCartaz;
          if (_cursor.isNull(_cursorIndexOfImagemCartaz)) {
            _tmpImagemCartaz = null;
          } else {
            _tmpImagemCartaz = _cursor.getString(_cursorIndexOfImagemCartaz);
          }
          final String _tmpSinopse;
          if (_cursor.isNull(_cursorIndexOfSinopse)) {
            _tmpSinopse = null;
          } else {
            _tmpSinopse = _cursor.getString(_cursorIndexOfSinopse);
          }
          final long _tmpDataLancamento;
          _tmpDataLancamento = _cursor.getLong(_cursorIndexOfDataLancamento);
          final double _tmpAvaliacaoIMDB;
          _tmpAvaliacaoIMDB = _cursor.getDouble(_cursorIndexOfAvaliacaoIMDB);
          _tmpInfo = new InfoDB(_tmpLinkInfo,_tmpName,_tmpImagemCartaz,_tmpSinopse,_tmpDataLancamento,_tmpAvaliacaoIMDB);
          ArrayList<GenreDB> _tmpGenresCollection_1 = null;
          final String _tmpKey_1 = _cursor.getString(_cursorIndexOfLinkInfo);
          _tmpGenresCollection_1 = _collectionGenres.get(_tmpKey_1);
          if (_tmpGenresCollection_1 == null) {
            _tmpGenresCollection_1 = new ArrayList<GenreDB>();
          }
          _item = new InfoWithGenres(_tmpInfo,_tmpGenresCollection_1);
          _result.add(_item);
        }
        __db.setTransactionSuccessful();
        return _result;
      } finally {
        _cursor.close();
        _statement.release();
      }
    } finally {
      __db.endTransaction();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private void __fetchRelationshipfilmesAsptUlusofonaDeisiCm2223G2200581322004525DataLocalFilmeDB(
      final ArrayMap<String, ArrayList<FilmeDB>> _map) {
    final Set<String> __mapKeySet = _map.keySet();
    if (__mapKeySet.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      ArrayMap<String, ArrayList<FilmeDB>> _tmpInnerMap = new ArrayMap<String, ArrayList<FilmeDB>>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _tmpIndex = 0;
      int _mapIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshipfilmesAsptUlusofonaDeisiCm2223G2200581322004525DataLocalFilmeDB(_tmpInnerMap);
          _tmpInnerMap = new ArrayMap<String, ArrayList<FilmeDB>>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshipfilmesAsptUlusofonaDeisiCm2223G2200581322004525DataLocalFilmeDB(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `idFilme`,`idCinema`,`linkInfo`,`avaliacaoUtilizador`,`data`,`observacoes` FROM `filmes` WHERE `linkInfo` IN (");
    final int _inputSize = __mapKeySet.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (String _item : __mapKeySet) {
      if (_item == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, _item);
      }
      _argIndex ++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "linkInfo");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfIdFilme = 0;
      final int _cursorIndexOfIdCinema = 1;
      final int _cursorIndexOfLinkInfo = 2;
      final int _cursorIndexOfAvaliacaoUtilizador = 3;
      final int _cursorIndexOfData = 4;
      final int _cursorIndexOfObservacoes = 5;
      while(_cursor.moveToNext()) {
        final String _tmpKey = _cursor.getString(_itemKeyIndex);
        ArrayList<FilmeDB> _tmpRelation = _map.get(_tmpKey);
        if (_tmpRelation != null) {
          final FilmeDB _item_1;
          final String _tmpIdFilme;
          if (_cursor.isNull(_cursorIndexOfIdFilme)) {
            _tmpIdFilme = null;
          } else {
            _tmpIdFilme = _cursor.getString(_cursorIndexOfIdFilme);
          }
          final int _tmpIdCinema;
          _tmpIdCinema = _cursor.getInt(_cursorIndexOfIdCinema);
          final String _tmpLinkInfo;
          if (_cursor.isNull(_cursorIndexOfLinkInfo)) {
            _tmpLinkInfo = null;
          } else {
            _tmpLinkInfo = _cursor.getString(_cursorIndexOfLinkInfo);
          }
          final int _tmpAvaliacaoUtilizador;
          _tmpAvaliacaoUtilizador = _cursor.getInt(_cursorIndexOfAvaliacaoUtilizador);
          final long _tmpData;
          _tmpData = _cursor.getLong(_cursorIndexOfData);
          final String _tmpObservacoes;
          if (_cursor.isNull(_cursorIndexOfObservacoes)) {
            _tmpObservacoes = null;
          } else {
            _tmpObservacoes = _cursor.getString(_cursorIndexOfObservacoes);
          }
          _item_1 = new FilmeDB(_tmpIdFilme,_tmpIdCinema,_tmpLinkInfo,_tmpAvaliacaoUtilizador,_tmpData,_tmpObservacoes);
          _tmpRelation.add(_item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }

  private void __fetchRelationshipgenresAsptUlusofonaDeisiCm2223G2200581322004525DataLocalGenreDB(
      final ArrayMap<String, ArrayList<GenreDB>> _map) {
    final Set<String> __mapKeySet = _map.keySet();
    if (__mapKeySet.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      ArrayMap<String, ArrayList<GenreDB>> _tmpInnerMap = new ArrayMap<String, ArrayList<GenreDB>>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _tmpIndex = 0;
      int _mapIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshipgenresAsptUlusofonaDeisiCm2223G2200581322004525DataLocalGenreDB(_tmpInnerMap);
          _tmpInnerMap = new ArrayMap<String, ArrayList<GenreDB>>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshipgenresAsptUlusofonaDeisiCm2223G2200581322004525DataLocalGenreDB(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `genres`.`nameGenre` AS `nameGenre`,_junction.`linkInfo` FROM `InfoGenreCrossRef` AS _junction INNER JOIN `genres` ON (_junction.`nameGenre` = `genres`.`nameGenre`) WHERE _junction.`linkInfo` IN (");
    final int _inputSize = __mapKeySet.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (String _item : __mapKeySet) {
      if (_item == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, _item);
      }
      _argIndex ++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = 1; // _junction.linkInfo;
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfNameGenre = 0;
      while(_cursor.moveToNext()) {
        final String _tmpKey = _cursor.getString(_itemKeyIndex);
        ArrayList<GenreDB> _tmpRelation = _map.get(_tmpKey);
        if (_tmpRelation != null) {
          final GenreDB _item_1;
          final String _tmpNameGenre;
          if (_cursor.isNull(_cursorIndexOfNameGenre)) {
            _tmpNameGenre = null;
          } else {
            _tmpNameGenre = _cursor.getString(_cursorIndexOfNameGenre);
          }
          _item_1 = new GenreDB(_tmpNameGenre);
          _tmpRelation.add(_item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }

  private void __fetchRelationshipphotosAsptUlusofonaDeisiCm2223G2200581322004525DataLocalPhotoDB(
      final ArrayMap<String, ArrayList<PhotoDB>> _map) {
    final Set<String> __mapKeySet = _map.keySet();
    if (__mapKeySet.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      ArrayMap<String, ArrayList<PhotoDB>> _tmpInnerMap = new ArrayMap<String, ArrayList<PhotoDB>>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _tmpIndex = 0;
      int _mapIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshipphotosAsptUlusofonaDeisiCm2223G2200581322004525DataLocalPhotoDB(_tmpInnerMap);
          _tmpInnerMap = new ArrayMap<String, ArrayList<PhotoDB>>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshipphotosAsptUlusofonaDeisiCm2223G2200581322004525DataLocalPhotoDB(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `photoStr`,`idFilme` FROM `photos` WHERE `idFilme` IN (");
    final int _inputSize = __mapKeySet.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (String _item : __mapKeySet) {
      if (_item == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, _item);
      }
      _argIndex ++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "idFilme");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfPhotoStr = 0;
      final int _cursorIndexOfIdFilme = 1;
      while(_cursor.moveToNext()) {
        final String _tmpKey = _cursor.getString(_itemKeyIndex);
        ArrayList<PhotoDB> _tmpRelation = _map.get(_tmpKey);
        if (_tmpRelation != null) {
          final PhotoDB _item_1;
          final String _tmpPhotoStr;
          if (_cursor.isNull(_cursorIndexOfPhotoStr)) {
            _tmpPhotoStr = null;
          } else {
            _tmpPhotoStr = _cursor.getString(_cursorIndexOfPhotoStr);
          }
          final String _tmpIdFilme;
          if (_cursor.isNull(_cursorIndexOfIdFilme)) {
            _tmpIdFilme = null;
          } else {
            _tmpIdFilme = _cursor.getString(_cursorIndexOfIdFilme);
          }
          _item_1 = new PhotoDB(_tmpPhotoStr,_tmpIdFilme);
          _tmpRelation.add(_item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
