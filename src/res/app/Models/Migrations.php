<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;
use Kyslik\ColumnSortable\Sortable;

class Migrations extends Model
{
    use Sortable;

    protected $table = 'migrations';
    public $timestamps = false;

    protected $primaryKey = 'id';
    protected $fillable = [

        'migration',
        'batch',
    ];

    public $sortable = [

        'migration',
        'batch',
    ];

}
