<?php


namespace App\Http\Filters;
use App\Http\Filters\QueryFilter;
use Illuminate\Database\Eloquent\Builder;

trait Filterable
{
    public function scopeFilter($query, QueryFilter $filters)
    {
        return $filters->apply($query);
    }

}
