<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;
use Kyslik\ColumnSortable\Sortable;

class PasswordResetTokens extends Model
{
    use Sortable;

    protected $table = 'password_reset_tokens';
    public $timestamps = false;

    protected $primaryKey = 'email';
    protected $fillable = [

        'token',
        'created_at',
    ];

    public $sortable = [

        'token',
        'created_at',
    ];

}
