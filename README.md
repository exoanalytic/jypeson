jypeson
=======

A curious way to document JSON

What is it?
-----------
A Java library that ingests JSON and spits it back out with HTML-hyperlinked keys. What are the keys hyperlinked to? You decide.

Why?
----
An application I've been writing consists of multiple JSON configuration files. This application also has a web interface. The
web interface displays the JSON configuration files sometimes, and I wanted inline documentation on those pages. Specifically,
I wanted tooltip hovers for the JSON keys (or names) that would display documentation about each configuration value.

What?
-----
Here's an example:

    {
        "name":"mild",
        "upshift": {
            "first": 4000,
            "second": 3000,
            "third": 3000
        },
        "downshift": {
            "second": 2000,
            "third": 1800,
        }
    }

That's great, but what's it mean? This is what it means (roll-over the hyperlinks):

<pre>
    {
        <a title="Name of this config" href="">"name"</a>:"mild",  
        <a title="Describes when to upshift" href="">"upshift"</a>: {  
            <a title="Engine RPM at which to upshift from 1st" href="">"first"</a>: 4000,  
            <a title="When to upshift from 2nd" href="">"second"</a>: 3000,  
            <a title="Up to 4th at this RPM" href="">"third"</a>: 3000  
        },
        <a title="Downshift according to these settings" href="">"downshift"</a>: {  
            <a title="Downshift to first at this RPM" href="">"second"</a>: 1200,  
            <a title="Grab 2nd at this RPM" href="">"third"</a>: 1800,  
        }  
    }
</pre>  

