<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;
use Kyslik\ColumnSortable\Sortable;

class Mois extends Model
{
    use Sortable;

    protected $table = 'mois';
    public $timestamps = false;

    protected $primaryKey = 'm_id';
    protected $fillable = [

        'm_designation',
        'm_position',
    ];

    public $sortable = [

        'm_designation',
        'm_position',
    ];

}
