<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;
use Kyslik\ColumnSortable\Sortable;

class CacheLocks extends Model
{
    use Sortable;

    protected $table = 'cache_locks';
    public $timestamps = false;

    protected $primaryKey = 'key';
    protected $fillable = [

        'owner',
        'expiration',
    ];

    public $sortable = [

        'owner',
        'expiration',
    ];

}
