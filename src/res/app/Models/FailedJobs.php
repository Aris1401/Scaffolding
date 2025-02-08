<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;
use Kyslik\ColumnSortable\Sortable;

class FailedJobs extends Model
{
    use Sortable;

    protected $table = 'failed_jobs';
    public $timestamps = false;

    protected $primaryKey = 'id';
    protected $fillable = [

        'uuid',
        'connection',
        'queue',
        'payload',
        'exception',
        'failed_at',
    ];

    public $sortable = [

        'uuid',
        'connection',
        'queue',
        'payload',
        'exception',
        'failed_at',
    ];

}
