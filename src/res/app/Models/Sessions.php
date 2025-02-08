<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;
use Kyslik\ColumnSortable\Sortable;

class Sessions extends Model
{
    use Sortable;

    protected $table = 'sessions';
    public $timestamps = false;

    protected $primaryKey = 'id';
    protected $fillable = [

        'user_id',
        'ip_address',
        'user_agent',
        'payload',
        'last_activity',
    ];

    public $sortable = [

        'user_id',
        'ip_address',
        'user_agent',
        'payload',
        'last_activity',
    ];

}
