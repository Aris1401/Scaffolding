<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;
use Kyslik\ColumnSortable\Sortable;

class Cache extends Model
{
    use Sortable;

    protected $table = 'cache';
    public $timestamps = false;

    protected $primaryKey = 'key';
    protected $fillable = [

        'value',
        'expiration',
    ];

    public $sortable = [

        'value',
        'expiration',
    ];

}
